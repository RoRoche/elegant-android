package fr.guddy.elegantandroid.ui.repo

import android.app.Activity
import android.content.Intent
import fr.guddy.elegantandroid.Destination

class RepoActivityDestination(
    private val origin: Activity,
    private val intent: Intent
) : Destination {

    constructor(
        origin: Activity,
        repoId: Long
    ) : this(
        origin,
        Intent(origin.applicationContext, RepoActivity::class.java).apply {
            putExtras(
                RepoActivityExtra(repoId = repoId).toBundle()
            )
        }
    )

    override fun go() {
        origin.startActivity(intent)
    }

}