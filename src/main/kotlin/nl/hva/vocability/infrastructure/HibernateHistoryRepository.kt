package nl.hva.vocability.infrastructure

import nl.hva.vocability.domain.HistoryEntry
import nl.hva.vocability.domain.api.HistoryRepository
import nl.hva.vocability.domain.ids.HistoryEntryId
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery

@Repository
class HibernateHistoryRepository : HistoryRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun nextHistoryEntryIdentity(): HistoryEntryId = HistoryEntryId(UUID.randomUUID().toString())

    override fun getAllHistoryEntries(): List<HistoryEntry> {
        val query: TypedQuery<HistoryEntry> = this.entityManager.createQuery(
            "SELECT hist FROM HistoryEntry hist ORDER BY hist.date DESC", HistoryEntry::class.java
        )
        return query.resultList
    }

    override fun getHistoryEntryById(historyEntryId: HistoryEntryId): List<HistoryEntry> {
        val query: TypedQuery<HistoryEntry> = this.entityManager.createQuery(
            "SELECT hist FROM HistoryEntry hist WHERE hist.domainId = ?1", HistoryEntry::class.java
        )
        return query.setParameter(1, historyEntryId).resultList
    }

    override fun addHistoryEntry(historyEntry: HistoryEntry) {
        this.entityManager.persist(historyEntry)
    }

    override fun deleteAllHistoryEntries() {
        val query: TypedQuery<HistoryEntry> = this.entityManager.createQuery(
            "SELECT hist FROM HistoryEntry hist", HistoryEntry::class.java
        )
        val result: List<HistoryEntry> = query.resultList
        for (historyEntry in result) {
            this.entityManager.remove(historyEntry)
        }
    }

    override fun deleteHistoryEntryById(historyEntryId: HistoryEntryId) {
        val query: TypedQuery<HistoryEntry> = this.entityManager.createQuery(
            "SELECT hist FROM HistoryEntry hist WHERE hist.domainId = ?1", HistoryEntry::class.java
        )
        val result: HistoryEntry = query.setParameter(1, historyEntryId).singleResult
        this.entityManager.remove(result)
    }

}