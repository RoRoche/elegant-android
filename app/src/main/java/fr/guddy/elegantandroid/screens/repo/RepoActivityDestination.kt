package fr.guddy.elegantandroid.screens.repo

import android.app.Activity
import android.content.Intent
import fr.guddy.eoandroidui.Destination

class RepoActivityDestination(
    delegate: Destination
) : Destination.Wrap(
    delegate = delegate
) {
    constructor(
        origin: Activity,
        repoId: Long
    ) : this(
        Destination.ToActivity(
            origin = origin,
            intent = Intent(
                origin.applicationContext,
                RepoActivity::class.java
            ).apply {
                putExtras(
                    RepoActivityExtra(repoId = repoId).toBundle()
                )
            }
        )
    )
}