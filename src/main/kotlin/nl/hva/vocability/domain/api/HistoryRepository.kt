package nl.hva.vocability.domain.api

import nl.hva.vocability.domain.HistoryEntry
import nl.hva.vocability.domain.ids.HistoryEntryId

interface HistoryRepository {

    fun nextHistoryEntryIdentity(): HistoryEntryId

    fun getAllHistoryEntries(): List<HistoryEntry>

    fun getHistoryEntryById(historyEntryId: HistoryEntryId): List<HistoryEntry>

    fun addHistoryEntry(historyEntry: HistoryEntry)

    fun deleteAllHistoryEntries()

    fun deleteHistoryEntryById(historyEntryId: HistoryEntryId)

}