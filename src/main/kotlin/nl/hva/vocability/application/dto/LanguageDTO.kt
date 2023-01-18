package nl.hva.vocability.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.vocability.domain.Language

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class LanguageDTO {
    private lateinit var id: String
    private lateinit var name: String

    companion object {
        fun fromLanguage(language: Language): LanguageDTO {
            val languageDTO = LanguageDTO()

            languageDTO.id = language.domainId().id()
            languageDTO.name = language.name()

            return languageDTO
        }

        fun fromLanguages(languages: List<Language>): List<LanguageDTO> {
            val languageDTOs: ArrayList<LanguageDTO> = arrayListOf()

            for (language in languages) {
                languageDTOs.add(fromLanguage(language))
            }

            return languageDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun name(): String = this.name
}