package fr.guddy.elegantandroid.ui.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import fr.guddy.elegantandroid.*
import fr.guddy.elegantandroid.databinding.ActivityRepoBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepoActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable
    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoViewState: MutableLiveData<ViewState<ActivityRepoBinding>>
    private lateinit var dbHelper: ElegantAndroidDbHelper

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
        disposable = RxDbRepoById(
            db = dbHelper.readableDatabase,
            id = RepoActivityExtra(intent.extras!!).repoId
        ).single(
        ).subscribeOn(
            Schedulers.newThread()
        ).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe(
            { repo: Repo ->
                repoViewState.value = RepoViewState.WithContent(
                    DetailRepo(repo)
                )
            },
            {
                repoViewState.value = RepoViewState.IsError
            }
        )
    }

    override fun onDestroy() {
        dbHelper.close()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onDestroy()
    }
}
