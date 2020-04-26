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
//        setContentView(R.layout.activity_main)
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
//        val repos = listOf(
//                RoomRepo(1L, "name1", "desc1", "url1", "RoRoche"),
//                RoomRepo(2L, "name2", "desc2", "url2", "RoRoche"),
//                RoomRepo(3L, "name3", "desc3", "url3", "RoRoche"),
//                RoomRepo(4L, "name4", "desc4", "url4", "RoRoche"),
//                RoomRepo(5L, "name5", "desc5", "url5", "RoRoche"),
//                RoomRepo(6L, "name6", "desc6", "url6", "RoRoche"),
//                RoomRepo(7L, "name7", "desc7", "url7", "RoRoche"),
//                RoomRepo(8L, "name8", "desc8", "url8", "RoRoche"),
//                RoomRepo(9L, "name9", "desc9", "url9", "RoRoche"),
//                RoomRepo(10L, "name10", "desc10", "url10", "RoRoche"),
//                RoomRepo(11L, "name11", "desc11", "url11", "RoRoche"),
//                RoomRepo(12L, "name12", "desc12", "url12", "RoRoche"),
//                RoomRepo(13L, "name13", "desc13", "url13", "RoRoche"),
//                RoomRepo(14L, "name14", "desc14", "url14", "RoRoche"),
//                RoomRepo(15L, "name15", "desc15", "url15", "RoRoche"),
//                RoomRepo(16L, "name16", "desc16", "url16", "RoRoche"),
//                RoomRepo(17L, "name17", "desc17", "url17", "RoRoche"),
//                RoomRepo(18L, "name18", "desc18", "url18", "RoRoche"),
//                RoomRepo(19L, "name19", "desc19", "url19", "RoRoche"),
//                RoomRepo(20L, "name20", "desc20", "url20", "RoRoche")
//        )
//        setContent {
//            createListView2(repos)
//        }
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

//    @Composable
//    private fun createListView2(repos: List<RoomRepo>) {
//        MaterialTheme {
//            VerticalScroller {
//                Column {
//                    repos.forEach { repo ->
//                        repo.asListItem()
//                        Divider(color = Color.Blue, height = 1.dp)
//                    }
//                }
//            }
//        }
//    }
}

////@Entity(tableName = "REPO")
//data class RoomRepo(
////    @PrimaryKey
//    var id: Long = 0,
//    val name: String,
//    val description: String,
//    val url: String,
//    val user: String
//) {
//    @Composable
//    fun asListItem() {
//        Padding(left = 8.dp, right = 8.dp, top = 8.dp, bottom = 8.dp) {
//            FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
//                expanded(1.0f) {
//                    Text("$name - $description")
//                }
//                inflexible {
//                    Button(
//                            "Button $name",
//                            style = ContainedButtonStyle(),
//                            onClick = {
//                                Log.i(
//                                    "MainActivity",
//                                        "Item name $name"
//                                )
//                            })
//
//                }
//            }
//        }
//    }
//}

//@Dao
//interface RepoDao {
//    @Query("Select * FROM REPO")
//    fun all(): List<RoomRepo>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun add(repo: RoomRepo)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun addAll(repo: List<RoomRepo>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    @Transaction
//    fun addAll(vararg repos: RoomRepo)
//}
//
//@Database(entities = arrayOf(RoomRepo::class), version = 1)
//abstract class RepoDatabase : RoomDatabase() {
//    abstract fun repoDao(): RepoDao
//}
