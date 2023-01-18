package nl.hva.vocability.application.api

import nl.hva.vocability.application.dto.DifficultyLevelDTO
import nl.hva.vocability.application.dto.FlashcardDTO
import nl.hva.vocability.application.dto.LanguageDTO
import nl.hva.vocability.domain.ids.DifficultyLevelId
import nl.hva.vocability.domain.ids.FlashcardId
import nl.hva.vocability.domain.ids.LanguageId

interface AdministrationService {

    fun getLanguageById(languageId: LanguageId): List<LanguageDTO>

    fun getAllLanguages(): List<LanguageDTO>

    fun addLanguage(name: String)

    fun deleteLanguageById(languageId: LanguageId)

    fun getDifficultyLevelById(difficultyLevelId: DifficultyLevelId): List<DifficultyLevelDTO>

    fun getDifficultyLevelsByLanguageId(languageId: LanguageId): List<DifficultyLevelDTO>

    fun addDifficultyLevel(name: String, languageId: LanguageId)

    fun deleteDifficultyLevelById(difficultyLevelId: DifficultyLevelId)

    fun getFlashcardById(flashcardId: FlashcardId): List<FlashcardDTO>

    fun getFlashcardsByDifficultyLevelId(difficultyLevelId: DifficultyLevelId): List<FlashcardDTO>

    fun addFlashcard(front: String, back: String, difficultyLevelId: DifficultyLevelId)

    fun deleteFlashcardById(flashcardId: FlashcardId)

}