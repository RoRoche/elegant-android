package fr.guddy.elegantandroid.persistence.queries

import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.persistence.Table
import fr.guddy.elegantandroid.persistence.Persistable

class BulkInsert(
    private val db: SQLiteDatabase,
    private val into: Table,
    private val values: List<Persistable>
) : DbQuery<Unit> {

    override fun result() {
        db.beginTransaction();
        try {
            values.forEach { persistable ->
                db.insertWithOnConflict(
                    into.name(),
                    null,
                    persistable.toContentValues(),
                    SQLiteDatabase.CONFLICT_REPLACE
                )
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}