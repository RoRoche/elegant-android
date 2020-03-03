package fr.guddy.eoandroidpersistence.queries.rx

import fr.guddy.eoandroidpersistence.queries.DbQuery
import io.reactivex.Single

interface RxDbQuery<T> {
    fun single() : Single<T>

    abstract class WrapSyncDbQuery<T>(
        private val dbQuery: DbQuery<T>
    ) : RxDbQuery<T> {
        override fun single(): Single<T> {
            return Single.create { emitter ->
                try {
                    val result = dbQuery.result()
                    emitter.onSuccess(result)
                } catch (exception: Exception) {
                    emitter.onError(exception)
                }
            }
        }
    }
}