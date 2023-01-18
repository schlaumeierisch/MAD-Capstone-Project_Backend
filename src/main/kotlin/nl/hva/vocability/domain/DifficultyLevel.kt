package nl.hva.vocability.domain

import nl.hva.vocability.domain.ids.DifficultyLevelId
import nl.hva.vocability.domain.ids.LanguageId

open class DifficultyLevel {
    private val id: Long = 0
    private lateinit var domainId: DifficultyLevelId

    private lateinit var name: String

    // many-to-one
    private lateinit var languageDomainId: LanguageId

    // required by hibernate
    private constructor()

    constructor(
        domainId: DifficultyLevelId,
        name: String,
        languageDomainId: LanguageId
    ) {
        this.domainId = domainId
        this.name = name
        this.languageDomainId = languageDomainId
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): DifficultyLevelId = this.domainId
    fun name(): String = this.name
    fun languageDomainId(): LanguageId = this.languageDomainId
}