package fr.guddy.elegantandroid.screens.repo

import android.app.Activity
import android.content.Intent
import fr.guddy.eoandroidui.Destination

/**
 * Class representing the way to go to the [RepoActivity].
 *
 * @param delegate The delegate [Destination].
 */
class RepoActivityDestination(
    delegate: Destination
) : Destination.Wrap(
    delegate = delegate
) {

    /**
     * Secondary constructor that builds a [Destination.ToActivity] to go to [RepoActivity] with
     * proper [RepoActivityExtra].
     *
     * @param origin The [Activity] that wants to go to [RepoActivity].
     * @param repoId The ID of the repo to be displayed.
     */
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