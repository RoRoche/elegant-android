package fr.guddy.elegantandroid

import android.database.sqlite.SQLiteDatabase
import fr.guddy.elegantandroid.persistence.queries.*

class DbRepos(
    private val db: SQLiteDatabase
) : MutableList<Repo> {
    override fun contains(element: Repo): Boolean {
        return CountAll(
            db = db,
            from = RepoTable(),
            where = Where(
                selection = "id = ?",
                args = arrayOf(element.id().toString())
            )
        ).result() == 1
    }

    override fun isEmpty(): Boolean {
        return CountAll(
            db = db,
            from = RepoTable(),
            where = null
        ).result() == 0
    }

    override fun add(element: Repo): Boolean {
        Insert(
            db = db,
            into = RepoTable(),
            persistable = DbRepo(element)
        ).result()
        return true
    }

    override fun addAll(elements: Collection<Repo>): Boolean {
        BulkInsert(
            db = db,
            into = RepoTable(),
            values = elements.map { DbRepo(it) }
        ).result()
        return true
    }

    override fun remove(element: Repo): Boolean {
        return Delete(
            db = db,
            from = RepoTable(),
            where = Where(
                "id = ?",
                arrayOf(element.id().toString())
            )
        ).result() == 1
    }

    override val size: Int
        get() = CountAll(
            db = db,
            from = RepoTable()
        ).result()

    override fun containsAll(elements: Collection<Repo>): Boolean {
        TODO("not supported")
    }

    override fun get(index: Int): Repo {
        TODO("not supported")
    }

    override fun indexOf(element: Repo): Int {
        TODO("not supported")
    }

    override fun iterator(): MutableIterator<Repo> {
        TODO("not supported")
    }

    override fun lastIndexOf(element: Repo): Int {
        TODO("not supported")
    }

    override fun add(index: Int, element: Repo) {
        TODO("not supported")
    }

    override fun addAll(index: Int, elements: Collection<Repo>): Boolean {
        TODO("not supported")
    }

    override fun clear() {
        TODO("not supported")
    }

    override fun listIterator(): MutableListIterator<Repo> {
        TODO("not supported")
    }

    override fun listIterator(index: Int): MutableListIterator<Repo> {
        TODO("not supported")
    }

    override fun removeAll(elements: Collection<Repo>): Boolean {
        TODO("not supported")
    }

    override fun removeAt(index: Int): Repo {
        TODO("not supported")
    }

    override fun retainAll(elements: Collection<Repo>): Boolean {
        TODO("not supported")
    }

    override fun set(index: Int, element: Repo): Repo {
        TODO("not supported")
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<Repo> {
        TODO("not supported")
    }
}