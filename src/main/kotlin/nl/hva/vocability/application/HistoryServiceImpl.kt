package nl.hva.vocability.application

import nl.hva.vocability.application.api.HistoryService
import nl.hva.vocability.application.dto.HistoryEntryDTO
import nl.hva.vocability.domain.HistoryEntry
import nl.hva.vocability.domain.api.HistoryRepository
import nl.hva.vocability.domain.ids.HistoryEntryId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class HistoryServiceImpl : HistoryService {

    @Autowired
    private lateinit var historyRepository: HistoryRepository

    @Transactional
    override fun getAllHistoryEntries(): List<HistoryEntryDTO> {
        val historyEntries: List<HistoryEntry> = this.historyRepository.getAllHistoryEntries()

        return HistoryEntryDTO.fromHistoryEntries(historyEntries)
    }

    @Transactional
    override fun getHistoryEntryById(historyEntryId: HistoryEntryId): List<HistoryEntryDTO> {
        val historyEntry: List<HistoryEntry> = this.historyRepository.getHistoryEntryById(historyEntryId)

        return if (historyEntry.isNotEmpty()) {
            listOf(HistoryEntryDTO.fromHistoryEntry(historyEntry[0]))
        } else {
            emptyList()
        }
    }

    @Transactional
    override fun addHistoryEntry(
        language: String,
        difficultyLevel: String,
        totalAnswers: Int,
        correctAnswers: Int,
        date: LocalDateTime
    ) {
        val historyEntryId: HistoryEntryId = this.historyRepository.nextHistoryEntryIdentity()

        val historyEntry = HistoryEntry(
            historyEntryId, language, difficultyLevel, totalAnswers, correctAnswers, date
        )

        this.historyRepository.addHistoryEntry(historyEntry)
    }

    @Transactional
    override fun deleteAllHistoryEntries() {
        this.historyRepository.deleteAllHistoryEntries()
    }

    @Transactional
    override fun deleteHistoryEntryById(historyEntryId: HistoryEntryId) {
        this.historyRepository.deleteHistoryEntryById(historyEntryId)
    }

}