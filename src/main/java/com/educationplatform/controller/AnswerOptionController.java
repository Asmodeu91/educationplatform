package com.educationplatform.controller;

import com.educationplatform.model.AnswerOption;
import com.educationplatform.service.AnswerOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer-options")
public class AnswerOptionController {

    @Autowired
    private AnswerOptionService answerOptionService;

    @GetMapping
    public List<AnswerOption> getAllAnswerOptions() {
        return answerOptionService.getAllAnswerOptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerOption> getAnswerOptionById(@PathVariable Long id) {
        return answerOptionService.getAnswerOptionById(id)
                .map(option -> ResponseEntity.ok().body(option))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AnswerOption> createAnswerOption(@RequestBody AnswerOption answerOption) {
        AnswerOption createdOption = answerOptionService.createAnswerOption(answerOption);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOption);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerOption> updateAnswerOption(@PathVariable Long id, @RequestBody AnswerOption answerOptionDetails) {
        AnswerOption updatedOption = answerOptionService.updateAnswerOption(id, answerOptionDetails);
        return ResponseEntity.ok(updatedOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerOption(@PathVariable Long id) {
        answerOptionService.deleteAnswerOption(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/question/{questionId}")
    public List<AnswerOption> getAnswerOptionsByQuestionId(@PathVariable Long questionId) {
        return answerOptionService.getAnswerOptionsByQuestionId(questionId);
    }
}