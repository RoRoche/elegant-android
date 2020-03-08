package fr.guddy.eoandroidpersistence.queries

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Table

class CountAll(
    private val origin: DbQuery<Cursor>
) : DbQuery<Int> {

    constructor(
        db: SQLiteDatabase,
        from: Table,
        where: Where? = null
    ) : this(
        Select(
            db = db,
            columns = listOf(COUNT_ALL),
            from = from,
            where = where
        )
    )

    override fun result(): Int {
        return origin.result().let {
            it.moveToFirst()
            it.getInt(
                it.getColumnIndexOrThrow(COUNT_ALL)
            )
        }
    }

    companion object Constant {
        const val COUNT_ALL = "COUNT(*)"
    }
}