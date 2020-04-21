package fr.guddy.eoandroidpersistence.orders

import android.database.sqlite.SQLiteDatabase

/**
 * Interface that represents a SQL order.
 */
interface SqlOrder {
    /**
     * Perform the SQL order.
     */
    fun execute(db: SQLiteDatabase)

    /**
     * Convenient class that wraps multiple SQL orders to execute sequentially.
     *
     * @property orders All the SQL order to execute.
     */
    class Sequential(
        private val orders: List<SqlOrder>
    ) : SqlOrder {
        /**
         * Perform all SQL orders.
         */
        override fun execute(db: SQLiteDatabase) {
            this.orders.forEach {
                it.execute(db)
            }
        }
    }
}
