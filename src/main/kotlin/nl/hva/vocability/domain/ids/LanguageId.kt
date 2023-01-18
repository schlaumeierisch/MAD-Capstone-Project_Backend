package nl.hva.vocability.domain.ids

class LanguageId {
    private lateinit var id: String

    // required by hibernate
    private constructor()

    constructor(id: String) {
        this.id = id
    }

    // getter
    fun id(): String = this.id
}