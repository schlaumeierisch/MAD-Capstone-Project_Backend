package nl.hva.vocability.domain

import nl.hva.vocability.domain.ids.HistoryEntryId
import java.time.LocalDateTime

class HistoryEntry {
    private val id: Long = 0
    private lateinit var domainId: HistoryEntryId

    private lateinit var language: String
    private lateinit var difficultyLevel: String
    private var totalAnswers: Int = 0
    private var correctAnswers: Int = 0
    private lateinit var date: LocalDateTime

    // required by hibernate
    private constructor()

    constructor(
        domainId: HistoryEntryId,
        language: String,
        difficultyLevel: String,
        totalAnswers: Int,
        correctAnswers: Int,
        date: LocalDateTime
    ) {
        this.domainId = domainId
        this.language = language
        this.difficultyLevel = difficultyLevel
        this.totalAnswers = totalAnswers
        this.correctAnswers = correctAnswers
        this.date = date
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): HistoryEntryId = this.domainId
    fun language(): String = this.language
    fun difficultyLevel(): String = this.difficultyLevel
    fun totalAnswers(): Int = this.totalAnswers
    fun correctAnswers(): Int = this.correctAnswers
    fun date(): LocalDateTime = this.date
}