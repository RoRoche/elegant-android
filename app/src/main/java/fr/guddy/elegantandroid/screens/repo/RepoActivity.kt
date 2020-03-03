package fr.guddy.elegantandroid.screens.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import fr.guddy.elegantandroid.*
import fr.guddy.eoandroidconcurrency.Callback
import fr.guddy.eoandroidconcurrency.Job
import fr.guddy.eoandroidconcurrency.SimpleAsyncJob
import fr.guddy.elegantandroid.databinding.ActivityRepoBinding
import fr.guddy.elegantandroid.ui.MutableViewState
import fr.guddy.elegantandroid.ui.ViewState
import java.lang.ref.WeakReference
import java.util.concurrent.Callable

class RepoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoViewState: MutableLiveData<ViewState<ActivityRepoBinding>>
    private lateinit var dbHelper: ElegantAndroidDbHelper
    private var job: Job? = null

    private class OnRepoLoaded(
        private val activity: WeakReference<RepoActivity>
    ) : Callback<Repo> {
        constructor(activity: RepoActivity) : this(WeakReference(activity))

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
        private val activity: WeakReference<RepoActivity>
    ) : Callback.OnError {
        constructor(activity: RepoActivity) : this(WeakReference(activity))

        override fun accept(data: Throwable) {
            activity.get()?.repoViewState?.value = RepoViewState.IsError
        }

        override fun dispose() {
            activity.clear()
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
                    SelectRepoById(
                        db = dbHelper.readableDatabase,
                        id = RepoActivityExtra(intent.extras!!).repoId
                    )
                )
            },
            onSuccess = Callback.OnMainThread(
                delegate = OnRepoLoaded(this)
            ),
            onError = Callback.OnMainThread(
                OnRepoLoadingError(this)
            )
        )
        job?.run()
    }

    override fun onDestroy() {
        dbHelper.close()
        job?.dispose()
        super.onDestroy()
    }
}
