package fr.guddy.elegantandroid.ui.repo

import android.os.Bundle
import fr.guddy.elegantandroid.Extra

class RepoActivityExtra(val repoId: Long) : Extra {

    constructor(bundle: Bundle) : this(
        bundle.getLong("repo_id")
    )

    override fun toBundle() = Bundle().apply {
        putLong("repo_id", repoId)
    }
}