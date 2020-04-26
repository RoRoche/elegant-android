package com.github.roroche.elegantandroid.screens.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.github.roroche.elegantandroid.databinding.ActivityRepoBinding
import com.github.roroche.elegantandroid.domain.Repo
import com.github.roroche.elegantandroid.persistence.DbRepoById
import com.github.roroche.elegantandroid.persistence.ElegantAndroidDbHelper
import com.github.roroche.elegantandroid.ui.DetailRepo
import com.github.roroche.eoandroidconcurrency.Callback
import com.github.roroche.eoandroidconcurrency.Job
import com.github.roroche.eoandroidconcurrency.SimpleAsyncJob
import com.github.roroche.eoandroidui.MutableViewState
import com.github.roroche.eoandroidui.ViewState
import java.util.concurrent.Callable

/**
 * [AppCompatActivity] to display a single [Repo].
 */
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
            applicationContext
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
