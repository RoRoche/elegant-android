package fr.guddy.elegantandroid

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.queries.DbQuery
import fr.guddy.eoandroidpersistence.queries.rx.RxDbQuery
import io.reactivex.Single

class RxDbReposByOwner(private val origin: DbQuery<Cursor>) :
    RxDbQuery<List<Repo>> {
    constructor(
        db: SQLiteDatabase,
        owner: String
    ) : this(
        SelectReposByOwner(
            db = db,
            owner = owner
        )
    )

    override fun single(): Single<List<Repo>> {
        return Single.create { emitter ->
            try {
                emitter.onSuccess(DbReposByOwner(this.origin))
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }
}