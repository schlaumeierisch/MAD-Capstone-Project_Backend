package nl.hva.vocability.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.vocability.domain.Flashcard

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class FlashcardDTO {
    private lateinit var id: String
    private lateinit var front: String
    private lateinit var back: String

    // many-to-one
    private lateinit var difficultyLevelId: String

    companion object {
        fun fromFlashcard(flashcard: Flashcard): FlashcardDTO {
            val flashcardDTO = FlashcardDTO()

            flashcardDTO.id = flashcard.domainId().id()
            flashcardDTO.front = flashcard.front()
            flashcardDTO.back = flashcard.back()
            flashcardDTO.difficultyLevelId = flashcard.difficultyLevelDomainId().id()

            return flashcardDTO
        }

        fun fromFlashcards(flashcards: List<Flashcard>): List<FlashcardDTO> {
            val flashcardDTOs: ArrayList<FlashcardDTO> = arrayListOf()

            for (flashcard in flashcards) {
                flashcardDTOs.add(fromFlashcard(flashcard))
            }

            return flashcardDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun front(): String = this.front
    fun back(): String = this.back
    fun difficultyLevelId(): String = this.difficultyLevelId
}