package nl.hva.vocability.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.vocability.domain.HistoryEntry
import java.time.LocalDateTime

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class HistoryEntryDTO {
    private lateinit var id: String
    private lateinit var language: String
    private lateinit var difficultyLevel: String
    private var totalAnswers: Int = 0
    private var correctAnswers: Int = 0
    private lateinit var date: LocalDateTime

    companion object {
        fun fromHistoryEntry(historyEntry: HistoryEntry): HistoryEntryDTO {
            val historyEntryDTO = HistoryEntryDTO()

            historyEntryDTO.id = historyEntry.domainId().id()
            historyEntryDTO.language = historyEntry.language()
            historyEntryDTO.difficultyLevel = historyEntry.difficultyLevel()
            historyEntryDTO.totalAnswers = historyEntry.totalAnswers()
            historyEntryDTO.correctAnswers = historyEntry.correctAnswers()
            historyEntryDTO.date = historyEntry.date()

            return historyEntryDTO
        }

        fun fromHistoryEntries(historyEntries: List<HistoryEntry>): List<HistoryEntryDTO> {
            val historyEntryDTOs: ArrayList<HistoryEntryDTO> = arrayListOf()

            for (historyEntry in historyEntries) {
                historyEntryDTOs.add(fromHistoryEntry(historyEntry))
            }

            return historyEntryDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun language(): String = this.language
    fun difficultyLevel(): String = this.difficultyLevel
    fun totalAnswers(): Int = this.totalAnswers
    fun correctAnswers(): Int = this.correctAnswers
    fun date(): LocalDateTime = this.date
}