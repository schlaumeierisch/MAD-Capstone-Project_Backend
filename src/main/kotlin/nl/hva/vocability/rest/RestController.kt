package nl.hva.vocability.rest

import nl.hva.vocability.application.api.AdministrationService
import nl.hva.vocability.application.api.HistoryService
import nl.hva.vocability.application.dto.DifficultyLevelDTO
import nl.hva.vocability.application.dto.FlashcardDTO
import nl.hva.vocability.application.dto.HistoryEntryDTO
import nl.hva.vocability.application.dto.LanguageDTO
import nl.hva.vocability.domain.ids.DifficultyLevelId
import nl.hva.vocability.domain.ids.FlashcardId
import nl.hva.vocability.domain.ids.HistoryEntryId
import nl.hva.vocability.domain.ids.LanguageId
import nl.hva.vocability.rest.exceptions.NotExistingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/rest")
class RestController {

    @Autowired
    private lateinit var administrationService: AdministrationService

    @Autowired
    private lateinit var historyService: HistoryService

    @GetMapping("/languages/getAll")
    @ResponseBody
    fun getAllLanguages(): ResponseEntity<List<LanguageDTO>> {
        val languageDTOs: List<LanguageDTO> = this.administrationService.getAllLanguages()

        return ResponseEntity.status(HttpStatus.OK).body(languageDTOs)
    }

    @GetMapping("/languages/getById/{id}")
    @ResponseBody
    fun getLanguageById(
        @PathVariable("id") id: String
    ): ResponseEntity<LanguageDTO> {
        val languageDTO: List<LanguageDTO> = this.administrationService.getLanguageById(LanguageId(id))

        if (languageDTO.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(languageDTO[0])
        } else {
            throw NotExistingException("Language with id \'$id\' does not exist.")
        }
    }

    @PostMapping("/languages/add")
    fun addLanguage(
        name: String
    ): ResponseEntity<String> {
        this.administrationService.addLanguage(name)

        return ResponseEntity.status(HttpStatus.OK).body("New language added.")
    }

    @DeleteMapping("/languages/deleteById/{id}")
    @ResponseBody
    fun deleteLanguageById(
        @PathVariable("id") id: String
    ): ResponseEntity<String> {
        val languageDTO: List<LanguageDTO> = this.administrationService.getLanguageById(LanguageId(id))

        if (languageDTO.isNotEmpty()) {
            this.administrationService.deleteLanguageById(LanguageId(id))

            return ResponseEntity.status(HttpStatus.OK).body("Language with id \'$id\' successfully deleted.")
        } else {
            throw NotExistingException("Language with id \'$id\' does not exist.")
        }
    }

    @GetMapping("/difficultyLevels/getByLanguageId/{id}")
    @ResponseBody
    fun getDifficultyLevelsByLanguageId(
        @PathVariable("id") id: String
    ): ResponseEntity<List<DifficultyLevelDTO>> {
        val languageDTO: List<LanguageDTO> = this.administrationService.getLanguageById(LanguageId(id))

        if (languageDTO.isNotEmpty()) {
            val difficultyLevelDTOs: List<DifficultyLevelDTO> =
                this.administrationService.getDifficultyLevelsByLanguageId(LanguageId(id))

            return ResponseEntity.status(HttpStatus.OK).body(difficultyLevelDTOs)
        } else {
            throw NotExistingException("Language with id \'$id\' does not exist.")
        }
    }

    @PostMapping("/difficultyLevels/add")
    fun addDifficultyLevel(
        name: String,
        languageId: String
    ): ResponseEntity<String> {
        val languageDTO: List<LanguageDTO> = this.administrationService.getLanguageById(LanguageId(languageId))

        if (languageDTO.isNotEmpty()) {
            this.administrationService.addDifficultyLevel(name, LanguageId(languageId))

            return ResponseEntity.status(HttpStatus.OK)
                .body("New difficulty level for language id \'$languageId\' added.")
        } else {
            throw NotExistingException("Language with id \'$languageId\' does not exist.")
        }
    }

    @DeleteMapping("/difficultyLevels/deleteById/{id}")
    @ResponseBody
    fun deleteDifficultyLevelById(
        @PathVariable("id") id: String
    ): ResponseEntity<String> {
        val difficultyLevelDTO: List<DifficultyLevelDTO> =
            this.administrationService.getDifficultyLevelById(DifficultyLevelId(id))

        if (difficultyLevelDTO.isNotEmpty()) {
            this.administrationService.deleteDifficultyLevelById(DifficultyLevelId(id))

            return ResponseEntity.status(HttpStatus.OK).body("Difficulty level with id \'$id\' successfully deleted.")
        } else {
            throw NotExistingException("Difficulty level with id \'$id\' does not exist.")
        }
    }

    @GetMapping("/flashcards/getByDifficultyLevelId/{id}")
    @ResponseBody
    fun getFlashcardsByDifficultyLevelId(
        @PathVariable("id") id: String
    ): ResponseEntity<List<FlashcardDTO>> {
        val difficultyLevelDTO: List<DifficultyLevelDTO> = this.administrationService.getDifficultyLevelById(
            DifficultyLevelId(id)
        )

        if (difficultyLevelDTO.isNotEmpty()) {
            val flashcardDTOs: List<FlashcardDTO> =
                this.administrationService.getFlashcardsByDifficultyLevelId(DifficultyLevelId(id))

            return ResponseEntity.status(HttpStatus.OK).body(flashcardDTOs)
        } else {
            throw NotExistingException("Difficulty level with id \'$id\' does not exist.")
        }
    }

    @PostMapping("/flashcards/add")
    fun addFlashcard(
        front: String,
        back: String,
        difficultyLevelId: String
    ): ResponseEntity<String> {
        val difficultyLevelDTO: List<DifficultyLevelDTO> = this.administrationService.getDifficultyLevelById(
            DifficultyLevelId(difficultyLevelId)
        )

        if (difficultyLevelDTO.isNotEmpty()) {
            this.administrationService.addFlashcard(front, back, DifficultyLevelId(difficultyLevelId))

            return ResponseEntity.status(HttpStatus.OK)
                .body("New flashcard for difficulty level id \'$difficultyLevelId\' added.")
        } else {
            throw NotExistingException("Difficulty level with id \'$difficultyLevelId\' does not exist.")
        }
    }

    @DeleteMapping("/flashcards/deleteById/{id}")
    fun deleteFlashcardById(
        @PathVariable("id") id: String
    ): ResponseEntity<String> {
        val flashcardDTO: List<FlashcardDTO> = this.administrationService.getFlashcardById(FlashcardId(id))

        if (flashcardDTO.isNotEmpty()) {
            this.administrationService.deleteFlashcardById(FlashcardId(id))

            return ResponseEntity.status(HttpStatus.OK).body("Flashcard with id \'$id\' successfully deleted.")
        } else {
            throw NotExistingException("Flashcard with id \'$id\' does not exist.")
        }
    }

    @GetMapping("/historyEntries/getAll")
    @ResponseBody
    fun getAllHistoryEntries(): ResponseEntity<List<HistoryEntryDTO>> {
        val historyEntryDTOs: List<HistoryEntryDTO> = this.historyService.getAllHistoryEntries()

        return ResponseEntity.status(HttpStatus.OK).body(historyEntryDTOs)
    }

    @GetMapping("/historyEntries/getById/{id}")
    @ResponseBody
    fun getHistoryEntryById(
        @PathVariable("id") id: String
    ): ResponseEntity<HistoryEntryDTO> {
        val historyEntryDTO: List<HistoryEntryDTO> = this.historyService.getHistoryEntryById(HistoryEntryId(id))

        if (historyEntryDTO.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(historyEntryDTO[0])
        } else {
            throw NotExistingException("History entry with id \'$id\' does not exist.")
        }
    }

    // SwaggerUI: LocalDateTime must be passed in the format '2022-12-12T14:00:00.000'
    @PostMapping("/historyEntries/add")
    fun addHistoryEntry(
        language: String,
        difficultyLevel: String,
        totalAnswers: Int,
        correctAnswers: Int,
        @RequestParam("date")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) date: LocalDateTime
    ): ResponseEntity<String> {
        this.historyService.addHistoryEntry(language, difficultyLevel, totalAnswers, correctAnswers, date)

        return ResponseEntity.status(HttpStatus.OK)
            .body("New history entry for language \'$language\' & difficulty level \'$difficultyLevel\' added.")
    }

    @DeleteMapping("/historyEntries/deleteAll")
    @ResponseBody
    fun deleteAllHistoryEntries(): ResponseEntity<String>  {
        this.historyService.deleteAllHistoryEntries()

        return ResponseEntity.status(HttpStatus.OK).body("All history entries successfully deleted.")
    }

    @DeleteMapping("/historyEntries/deleteById/{id}")
    fun deleteHistoryEntryById(
        @PathVariable("id") id: String
    ): ResponseEntity<String> {
        val historyEntryDTO: List<HistoryEntryDTO> = this.historyService.getHistoryEntryById(HistoryEntryId(id))

        if (historyEntryDTO.isNotEmpty()) {
            this.historyService.deleteHistoryEntryById(HistoryEntryId(id))

            return ResponseEntity.status(HttpStatus.OK).body("History entry with id \'$id\' successfully deleted.")
        } else {
            throw NotExistingException("History entry with id \'$id\' does not exist.")
        }
    }

}