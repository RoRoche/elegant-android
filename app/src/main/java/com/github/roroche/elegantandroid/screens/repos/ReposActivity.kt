package com.github.roroche.elegantandroid.screens.repos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.github.roroche.elegantandroid.R
import com.github.roroche.elegantandroid.databinding.ReposBinding
import com.github.roroche.elegantandroid.domain.Repo
import com.github.roroche.elegantandroid.persistence.DbReposByOwner
import com.github.roroche.elegantandroid.persistence.ElegantAndroidDbHelper
import com.github.roroche.elegantandroid.persistence.queries.SelectReposByOwner
import com.github.roroche.elegantandroid.screens.repo.RepoActivityDestination
import com.github.roroche.elegantandroid.workmanager.GetReposForUserManagedRestRequest
import com.github.roroche.eoandroidconcurrency.Callback
import com.github.roroche.eoandroidconcurrency.Job
import com.github.roroche.eoandroidconcurrency.SimpleAsyncJob
import com.github.roroche.eoandroidui.MutableViewState
import com.github.roroche.eoandroidui.ViewState
import java.util.concurrent.Callable

/**
 * [AppCompatActivity] to display a list of [Repo].
 */
class ReposActivity : AppCompatActivity(), OnRepoClickListener {

    private var job: Job? = null
    private lateinit var dbHelper: ElegantAndroidDbHelper
    private lateinit var binding: ReposBinding
    private lateinit var reposViewState: MutableLiveData<ViewState<ReposBinding>>

    private class OnReposLoaded(
        activity: ReposActivity
    ) : Callback.InActivity<ReposActivity, List<Repo>>(activity) {

        override fun accept(data: List<Repo>) {
            if (data.isNullOrEmpty()) {
                activity.get()?.reposViewState?.value = ReposViewState.IsEmpty
            } else {
                activity.get()?.reposViewState?.value =
                    ReposViewState.WithContent(
                        activity.get()?.layoutInflater!!,
                        data,
                        activity.get()!!
                    )
            }
        }
    }

    private class OnReposLoadingError(
        activity: ReposActivity
    ) : Callback.InActivity<ReposActivity, Throwable>(activity) {
        override fun accept(data: Throwable) {
            activity.get()?.reposViewState?.value = ReposViewState.IsError
        }

        override fun dispose() {
            activity.clear()
        }
    }

    private fun loadReposFromDb() {
        job = SimpleAsyncJob<List<Repo>>(
            callable = Callable {
                DbReposByOwner(
                    SelectReposByOwner(
                        db = dbHelper.readableDatabase,
                        owner = "RoRoche"
                    ).result()
                )
            },
            onSuccess = Callback.OnMainThread(
                delegate = OnReposLoaded(this)
            ),
            onError = Callback.OnMainThread(
                OnReposLoadingError(this)
            )
        ).apply {
            run()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = ElegantAndroidDbHelper(
            applicationContext
        )
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
        reposViewState = MutableViewState(
            lifecycleOwner = this,
            binding = binding
        )
        reposViewState.value = ReposViewState.IsLoading

        GetReposForUserManagedRestRequest(
            workManager = WorkManager.getInstance(applicationContext),
            baseUrl = "https://api.github.com/",
            user = "RoRoche"
        ).workInfoLiveData().observe(
            this,
            Observer { workInfo ->
                when (workInfo?.state) {
                    WorkInfo.State.SUCCEEDED -> loadReposFromDb()
                    WorkInfo.State.FAILED -> {
                        Toast.makeText(
                            this,
                            R.string.http_repos_error,
                            Toast.LENGTH_LONG
                        ).show()
                        loadReposFromDb()
                    }
                    else -> {
                    }
                }
            }
        )
    }

    override fun onDestroy() {
        dbHelper.close()
        job?.dispose()
        super.onDestroy()
    }

    override fun onClickRepo(repo: Repo) {
        RepoActivityDestination(
            origin = this,
            repoId = repo.id()
        ).go()
    }
}
