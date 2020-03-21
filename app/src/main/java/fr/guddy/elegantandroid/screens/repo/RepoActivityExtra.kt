package fr.guddy.elegantandroid.screens.repo

import android.os.Bundle
import fr.guddy.eoandroidui.Extra

/**
 * Class representing [Extra] data to be passed to an [RepoActivity] to start.
 *
 * @property repoId The ID of the repo to be displayed.
 */
class RepoActivityExtra(val repoId: Long) : Extra {

    /**
     * Secondary constructor to get data from a [Bundle] object.
     *
     * @param bundle [Bundle] containing the repo's ID.
     */
    constructor(bundle: Bundle) : this(
        bundle.getLong("repo_id")
    )

    /**
     * Convert extra parameters to [Bundle].
     */
    override fun toBundle() = Bundle().apply {
        putLong("repo_id", repoId)
    }
}