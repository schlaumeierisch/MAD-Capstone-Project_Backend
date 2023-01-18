package nl.hva.vocability.infrastructure

import nl.hva.vocability.domain.DifficultyLevel
import nl.hva.vocability.domain.Flashcard
import nl.hva.vocability.domain.Language
import nl.hva.vocability.domain.api.AdministrationRepository
import nl.hva.vocability.domain.ids.DifficultyLevelId
import nl.hva.vocability.domain.ids.FlashcardId
import nl.hva.vocability.domain.ids.LanguageId
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery

@Repository
class HibernateAdministrationRepository : AdministrationRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun nextLanguageIdentity(): LanguageId = LanguageId(UUID.randomUUID().toString())

    override fun nextDifficultyLevelIdentity(): DifficultyLevelId = DifficultyLevelId(UUID.randomUUID().toString())

    override fun nextFlashcardIdentity(): FlashcardId = FlashcardId(UUID.randomUUID().toString())

    override fun getLanguageById(languageId: LanguageId): List<Language> {
        val query: TypedQuery<Language> = this.entityManager.createQuery(
            "SELECT lang FROM Language lang WHERE lang.domainId = ?1", Language::class.java
        )
        return query.setParameter(1, languageId).resultList
    }

    override fun getAllLanguages(): List<Language> {
        val query: TypedQuery<Language> = this.entityManager.createQuery(
            "SELECT lang FROM Language lang", Language::class.java
        )
        return query.resultList
    }

    override fun addLanguage(language: Language) {
        this.entityManager.persist(language)
    }

    override fun deleteLanguageById(languageId: LanguageId) {
        val query: TypedQuery<Language> = this.entityManager.createQuery(
            "SELECT lang FROM Language lang WHERE lang.domainId = ?1", Language::class.java
        )
        val result: Language = query.setParameter(1, languageId).singleResult
        this.entityManager.remove(result)
    }

    override fun getDifficultyLevelById(difficultyLevelId: DifficultyLevelId): List<DifficultyLevel> {
        val query: TypedQuery<DifficultyLevel> = this.entityManager.createQuery(
            "SELECT diff FROM DifficultyLevel diff WHERE diff.domainId = ?1", DifficultyLevel::class.java
        )
        return query.setParameter(1, difficultyLevelId).resultList
    }

    override fun getDifficultyLevelsByLanguageId(languageId: LanguageId): List<DifficultyLevel> {
        val query: TypedQuery<DifficultyLevel> = this.entityManager.createQuery(
            "SELECT diff FROM DifficultyLevel diff WHERE diff.languageDomainId = ?1", DifficultyLevel::class.java
        )
        return query.setParameter(1, languageId).resultList
    }

    override fun addDifficultyLevel(difficultyLevel: DifficultyLevel) {
        this.entityManager.persist(difficultyLevel)
    }

    override fun deleteDifficultyLevelById(difficultyLevelId: DifficultyLevelId) {
        val query: TypedQuery<DifficultyLevel> = this.entityManager.createQuery(
            "SELECT diff FROM DifficultyLevel diff WHERE diff.domainId = ?1", DifficultyLevel::class.java
        )
        val result: DifficultyLevel = query.setParameter(1, difficultyLevelId).singleResult
        this.entityManager.remove(result)
    }

    override fun getFlashcardById(flashcardId: FlashcardId): List<Flashcard> {
        val query: TypedQuery<Flashcard> = this.entityManager.createQuery(
            "SELECT fc FROM Flashcard fc WHERE fc.domainId = ?1", Flashcard::class.java
        )
        return query.setParameter(1, flashcardId).resultList
    }

    override fun getFlashcardsByDifficultyLevelId(difficultyLevelId: DifficultyLevelId): List<Flashcard> {
        val query: TypedQuery<Flashcard> = this.entityManager.createQuery(
            "SELECT fc FROM Flashcard fc WHERE fc.difficultyLevelDomainId = ?1", Flashcard::class.java
        )
        return query.setParameter(1, difficultyLevelId).resultList
    }

    override fun addFlashcard(flashcard: Flashcard) {
        this.entityManager.persist(flashcard)
    }

    override fun deleteFlashcardById(flashcardId: FlashcardId) {
        val query: TypedQuery<Flashcard> = this.entityManager.createQuery(
            "SELECT fc FROM Flashcard fc WHERE fc.domainId = ?1", Flashcard::class.java
        )
        val result: Flashcard = query.setParameter(1, flashcardId).singleResult
        this.entityManager.remove(result)
    }

}