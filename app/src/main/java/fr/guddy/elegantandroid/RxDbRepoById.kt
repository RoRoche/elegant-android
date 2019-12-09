package fr.guddy.elegantandroid

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import io.reactivex.Single

class RxDbRepoById(private val origin: DbQuery<Cursor>) : RxDbQuery<Repo> {
    constructor(
        db: SQLiteDatabase,
        id: Long
    ) : this(
        SelectRepoById(
            db = db,
            id = id
        )
    )

    override fun single(): Single<Repo> {
        return Single.create { emitter ->
            try {
                emitter.onSuccess(DbRepoById(this.origin))
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }
}