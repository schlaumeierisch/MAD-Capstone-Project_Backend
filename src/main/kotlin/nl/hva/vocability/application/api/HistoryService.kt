package nl.hva.vocability.application.api

import nl.hva.vocability.application.dto.HistoryEntryDTO
import nl.hva.vocability.domain.ids.HistoryEntryId
import java.time.LocalDateTime

interface HistoryService {

    fun getAllHistoryEntries(): List<HistoryEntryDTO>

    fun getHistoryEntryById(historyEntryId: HistoryEntryId): List<HistoryEntryDTO>

    fun addHistoryEntry(
        language: String,
        difficultyLevel: String,
        totalAnswers: Int,
        correctAnswers: Int,
        date: LocalDateTime
    )

    fun deleteAllHistoryEntries()

    fun deleteHistoryEntryById(historyEntryId: HistoryEntryId)

}