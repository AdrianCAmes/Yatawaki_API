package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.QuestionService;
import com.orchestrator.orchestrator.model.Question;
import com.orchestrator.orchestrator.model.dto.question.request.QuestionChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.question.request.QuestionCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.question.request.QuestionUpdateRequestDto;
import com.orchestrator.orchestrator.utils.QuestionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionUtils questionUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createQuestion(@RequestBody QuestionCreateRequestDto questionCreateRequestDto) {
        log.info("Post operation in /question");
        try {
            Question questionToSave = questionUtils.buildDomainFromCreateRequestDto(questionCreateRequestDto);
            Question savedQuestion = questionService.create(questionToSave);
            return new ResponseEntity<>(savedQuestion, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findQuestionById(@PathVariable("id") Long id) {
        log.info("Get operation in /question/{}", id);
        try {
            Question retrievedQuestion = questionService.findById(id);
            if (retrievedQuestion != null) {
                return new ResponseEntity<>(retrievedQuestion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllQuestions(){
        log.info("Get operation in /question");
        try {
            List<Question> retrievedQuestions = questionService.findAll();
            if (!retrievedQuestions.isEmpty()) {
                return new ResponseEntity<>(retrievedQuestions, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Questions not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeQuestion(@RequestBody QuestionChangeRequestDto questionChangeRequestDto) {
        log.info("Put operation in /question");
        try {
            Question questionToChange = questionUtils.buildDomainFromChangeRequestDto(questionChangeRequestDto);
            Question changedQuestion = questionService.change(questionToChange);
            return new ResponseEntity<>(changedQuestion, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateQuestion(@RequestBody QuestionUpdateRequestDto questionUpdateRequestDto) {
        log.info("Patch operation in /question");
        try {
            Question questionToUpdate = questionUtils.buildDomainFromUpdateRequestDto(questionUpdateRequestDto);
            Question updatedQuestion = questionService.update(questionToUpdate);
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable("id") Long id) {
        log.info("Delete operation in /question/{}", id);
        try {
            Question question = questionService.removeById(id);
            return new ResponseEntity<>(question, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
