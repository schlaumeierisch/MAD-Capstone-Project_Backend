package nl.hva.vocability.application.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import nl.hva.vocability.domain.DifficultyLevel

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class DifficultyLevelDTO {
    private lateinit var id: String
    private lateinit var name: String

    // many-to-one
    private lateinit var languageId: String

    companion object {
        fun fromDifficultyLevel(difficultyLevel: DifficultyLevel): DifficultyLevelDTO {
            val difficultyLevelDTO = DifficultyLevelDTO()

            difficultyLevelDTO.id = difficultyLevel.domainId().id()
            difficultyLevelDTO.name = difficultyLevel.name()
            difficultyLevelDTO.languageId = difficultyLevel.languageDomainId().id()

            return difficultyLevelDTO
        }

        fun fromDifficultyLevels(difficultyLevels: List<DifficultyLevel>): List<DifficultyLevelDTO> {
            val difficultyLevelDTOs: ArrayList<DifficultyLevelDTO> = arrayListOf()

            for (difficultyLevel in difficultyLevels) {
                difficultyLevelDTOs.add(fromDifficultyLevel(difficultyLevel))
            }

            return difficultyLevelDTOs
        }
    }

    // getter
    fun id(): String = this.id
    fun name(): String = this.name
    fun languageId(): String = this.languageId
}