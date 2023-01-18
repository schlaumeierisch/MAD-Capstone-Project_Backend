package nl.hva.vocability.domain.api

import nl.hva.vocability.domain.DifficultyLevel
import nl.hva.vocability.domain.Flashcard
import nl.hva.vocability.domain.Language
import nl.hva.vocability.domain.ids.DifficultyLevelId
import nl.hva.vocability.domain.ids.FlashcardId
import nl.hva.vocability.domain.ids.LanguageId

interface AdministrationRepository {

    fun nextLanguageIdentity(): LanguageId

    fun nextDifficultyLevelIdentity(): DifficultyLevelId

    fun nextFlashcardIdentity(): FlashcardId

    fun getLanguageById(languageId: LanguageId): List<Language>

    fun getAllLanguages(): List<Language>

    fun addLanguage(language: Language)

    fun deleteLanguageById(languageId: LanguageId)

    fun getDifficultyLevelById(difficultyLevelId: DifficultyLevelId): List<DifficultyLevel>

    fun getDifficultyLevelsByLanguageId(languageId: LanguageId): List<DifficultyLevel>

    fun addDifficultyLevel(difficultyLevel: DifficultyLevel)

    fun deleteDifficultyLevelById(difficultyLevelId: DifficultyLevelId)

    fun getFlashcardById(flashcardId: FlashcardId): List<Flashcard>

    fun getFlashcardsByDifficultyLevelId(difficultyLevelId: DifficultyLevelId): List<Flashcard>

    fun addFlashcard(flashcard: Flashcard)

    fun deleteFlashcardById(flashcardId: FlashcardId)

}