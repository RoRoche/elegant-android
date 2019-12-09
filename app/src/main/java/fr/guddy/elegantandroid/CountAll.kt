package fr.guddy.elegantandroid

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class CountAll(private val origin: DbQuery<Cursor>) : DbQuery<Int> {

    constructor(
        db: SQLiteDatabase,
        from: Table,
        where: Where? = null
    ) : this(
        Select(
            db = db,
            columns = listOf("COUNT(*)"),
            from = from,
            where = where
        )
    )

    override fun result(): Int {
        return origin.result().let {
            it.moveToFirst()
            it.getInt(it.getColumnIndexOrThrow("COUNT(*)"))
        }
    }
}