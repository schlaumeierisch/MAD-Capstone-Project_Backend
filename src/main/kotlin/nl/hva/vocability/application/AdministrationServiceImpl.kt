package nl.hva.vocability.application

import nl.hva.vocability.application.api.AdministrationService
import nl.hva.vocability.application.dto.DifficultyLevelDTO
import nl.hva.vocability.application.dto.FlashcardDTO
import nl.hva.vocability.application.dto.LanguageDTO
import nl.hva.vocability.domain.DifficultyLevel
import nl.hva.vocability.domain.Flashcard
import nl.hva.vocability.domain.Language
import nl.hva.vocability.domain.api.AdministrationRepository
import nl.hva.vocability.domain.ids.DifficultyLevelId
import nl.hva.vocability.domain.ids.FlashcardId
import nl.hva.vocability.domain.ids.LanguageId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdministrationServiceImpl : AdministrationService {

    @Autowired
    private lateinit var administrationRepository: AdministrationRepository

    @Transactional
    override fun getLanguageById(languageId: LanguageId): List<LanguageDTO> {
        val language: List<Language> = this.administrationRepository.getLanguageById(languageId)

        return if (language.isNotEmpty()) {
            listOf(LanguageDTO.fromLanguage(language[0]))
        } else {
            emptyList()
        }
    }

    @Transactional
    override fun getAllLanguages(): List<LanguageDTO> {
        val languages: List<Language> = this.administrationRepository.getAllLanguages()

        return LanguageDTO.fromLanguages(languages)
    }

    @Transactional
    override fun addLanguage(name: String) {
        val languageId: LanguageId = this.administrationRepository.nextLanguageIdentity()

        val language = Language(languageId, name)

        this.administrationRepository.addLanguage(language)
    }

    @Transactional
    override fun deleteLanguageById(languageId: LanguageId) {
        // delete associated difficulty levels (and flashcards)
        val difficultyLevels: List<DifficultyLevel> =
            this.administrationRepository.getDifficultyLevelsByLanguageId(languageId)
        for (difficultyLevel in difficultyLevels) {
            deleteDifficultyLevelById(difficultyLevel.domainId())
        }

        // delete language itself
        this.administrationRepository.deleteLanguageById(languageId)
    }

    @Transactional
    override fun getDifficultyLevelById(difficultyLevelId: DifficultyLevelId): List<DifficultyLevelDTO> {
        val difficultyLevel: List<DifficultyLevel> =
            this.administrationRepository.getDifficultyLevelById(difficultyLevelId)

        return if (difficultyLevel.isNotEmpty()) {
            listOf(DifficultyLevelDTO.fromDifficultyLevel(difficultyLevel[0]))
        } else {
            emptyList()
        }
    }

    @Transactional
    override fun getDifficultyLevelsByLanguageId(languageId: LanguageId): List<DifficultyLevelDTO> {
        val difficultyLevels: List<DifficultyLevel> =
            this.administrationRepository.getDifficultyLevelsByLanguageId(languageId)

        return DifficultyLevelDTO.fromDifficultyLevels(difficultyLevels)
    }

    @Transactional
    override fun addDifficultyLevel(name: String, languageId: LanguageId) {
        val difficultyLevelId: DifficultyLevelId = this.administrationRepository.nextDifficultyLevelIdentity()

        val difficultyLevel = DifficultyLevel(difficultyLevelId, name, languageId)

        this.administrationRepository.addDifficultyLevel(difficultyLevel)
    }

    @Transactional
    override fun deleteDifficultyLevelById(difficultyLevelId: DifficultyLevelId) {
        // delete associated flashcards
        val flashcards: List<Flashcard> =
            this.administrationRepository.getFlashcardsByDifficultyLevelId(difficultyLevelId)
        for (flashcard in flashcards) {
            deleteFlashcardById(flashcard.domainId())
        }

        // delete difficulty level itself
        this.administrationRepository.deleteDifficultyLevelById(difficultyLevelId)
    }

    @Transactional
    override fun getFlashcardById(flashcardId: FlashcardId): List<FlashcardDTO> {
        val flashcard: List<Flashcard> = this.administrationRepository.getFlashcardById(flashcardId)

        return if (flashcard.isNotEmpty()) {
            listOf(FlashcardDTO.fromFlashcard(flashcard[0]))
        } else {
            emptyList()
        }
    }

    @Transactional
    override fun getFlashcardsByDifficultyLevelId(difficultyLevelId: DifficultyLevelId): List<FlashcardDTO> {
        val flashcards: List<Flashcard> =
            this.administrationRepository.getFlashcardsByDifficultyLevelId(difficultyLevelId)

        return FlashcardDTO.fromFlashcards(flashcards)
    }

    @Transactional
    override fun addFlashcard(front: String, back: String, difficultyLevelId: DifficultyLevelId) {
        val flashcardId: FlashcardId = this.administrationRepository.nextFlashcardIdentity()

        val flashcard = Flashcard(flashcardId, front, back, difficultyLevelId)

        this.administrationRepository.addFlashcard(flashcard)
    }

    @Transactional
    override fun deleteFlashcardById(flashcardId: FlashcardId) {
        this.administrationRepository.deleteFlashcardById(flashcardId)
    }

}