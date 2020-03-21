package fr.guddy.elegantandroid.screens.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import fr.guddy.elegantandroid.databinding.ActivityRepoBinding
import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.elegantandroid.persistence.DbRepoById
import fr.guddy.elegantandroid.persistence.ElegantAndroidDbHelper
import fr.guddy.elegantandroid.persistence.RepoTable
import fr.guddy.elegantandroid.ui.DetailRepo
import fr.guddy.eoandroidconcurrency.Callback
import fr.guddy.eoandroidconcurrency.Job
import fr.guddy.eoandroidconcurrency.SimpleAsyncJob
import fr.guddy.eoandroidui.MutableViewState
import fr.guddy.eoandroidui.ViewState
import java.util.concurrent.Callable

class RepoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoViewState: MutableLiveData<ViewState<ActivityRepoBinding>>
    private lateinit var dbHelper: ElegantAndroidDbHelper
    private var job: Job? = null

    private class OnRepoLoaded(
        activity: RepoActivity
    ) : Callback.InActivity<RepoActivity, Repo>(activity) {

        override fun accept(data: Repo) {
            activity.get()?.repoViewState?.value = RepoViewState.WithContent(
                DetailRepo(data)
            )
        }

        override fun dispose() {
            activity.clear()
        }
    }

    private class OnRepoLoadingError(
        activity: RepoActivity
    ) : Callback.InActivity<RepoActivity, Throwable>(activity) {

        override fun accept(data: Throwable) {
            activity.get()?.repoViewState?.value = RepoViewState.IsError
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = ElegantAndroidDbHelper(
            applicationContext,
            listOf(RepoTable())
        )
        repoViewState = MutableViewState(
            lifecycleOwner = this,
            binding = binding
        )
        repoViewState.value = RepoViewState.IsLoading
        job = SimpleAsyncJob<Repo>(
            callable = Callable {
                DbRepoById(
                    db = dbHelper.readableDatabase,
                    id = RepoActivityExtra(intent.extras!!).repoId
                )
            },
            onSuccess = Callback.OnMainThread(
                delegate = OnRepoLoaded(this)
            ),
            onError = Callback.OnMainThread(
                OnRepoLoadingError(this)
            )
        ).apply {
            run()
        }
    }

    override fun onDestroy() {
        dbHelper.close()
        job?.dispose()
        super.onDestroy()
    }
}
