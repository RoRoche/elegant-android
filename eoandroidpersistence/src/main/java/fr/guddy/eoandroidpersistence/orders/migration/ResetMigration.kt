package fr.guddy.eoandroidpersistence.orders.migration

import fr.guddy.eoandroidpersistence.orders.SqlOrder

/**
 * Simple implementation of [Migration] that drops all the tables and re-create them fro scratch.
 *
 * @param sqlOrder The SQL to perform
 */
class ResetMigration(
    sqlOrder: SqlOrder
) : Migration.WithSqlOrder(sqlOrder) {

    /**
     * Secondary constructor that prepare a sequential SQL order with drop tables and create tables.
     *
     * @param dropTablesSqlOrder SQL order to drop all tables.
     * @param createTablesSqlOrder SQL order to create all tables.
     */
    constructor(
        dropTablesSqlOrder: SqlOrder,
        createTablesSqlOrder: SqlOrder
    ) : this(
        SqlOrder.Sequential(
            listOf(
                dropTablesSqlOrder,
                createTablesSqlOrder
            )
        )
    )
}