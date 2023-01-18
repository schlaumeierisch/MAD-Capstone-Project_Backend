package nl.hva.vocability.domain

import nl.hva.vocability.domain.ids.LanguageId

open class Language {
    private val id: Long = 0
    private lateinit var domainId: LanguageId

    private lateinit var name: String

    // required by hibernate
    private constructor()

    constructor(
        domainId: LanguageId,
        name: String
    ) {
        this.domainId = domainId
        this.name = name
    }

    // getter
    fun id(): Long = this.id
    fun domainId(): LanguageId = this.domainId
    fun name(): String = this.name
}