package nl.hva.vocability.domain

import nl.hva.vocability.domain.ids.DifficultyLevelId
import nl.hva.vocability.domain.ids.FlashcardId

open class Flashcard {
    private val id: Long = 0
    private lateinit var domainId: FlashcardId

    private lateinit var front: String
    private lateinit var back: String

    // many-to-one
    private lateinit var difficultyLevelDomainId: DifficultyLevelId

    // required by hibernate
    private constructor()

    constructor(
        domainId: FlashcardId,
        front: String,
        back: String,
        difficultyLevelDomainId: DifficultyLevelId
    ) {
        this.domainId = domainId
        this.front = front
        this.back = back
        this.difficultyLevelDomainId = difficultyLevelDomainId
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): FlashcardId = this.domainId
    fun front(): String = this.front
    fun back(): String = this.back
    fun difficultyLevelDomainId(): DifficultyLevelId = this.difficultyLevelDomainId
}