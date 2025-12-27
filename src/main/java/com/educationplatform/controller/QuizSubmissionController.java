package com.educationplatform.controller;

import com.educationplatform.model.QuizSubmission;
import com.educationplatform.service.QuizSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-submissions")
public class QuizSubmissionController {

    @Autowired
    private QuizSubmissionService quizSubmissionService;

    @GetMapping
    public List<QuizSubmission> getAllQuizSubmissions() {
        return quizSubmissionService.getAllQuizSubmissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizSubmission> getQuizSubmissionById(@PathVariable Long id) {
        return quizSubmissionService.getQuizSubmissionById(id)
                .map(submission -> ResponseEntity.ok().body(submission))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/submit")
    public QuizSubmission submitQuiz(
            @RequestParam Long quizId,
            @RequestParam Long studentId,
            @RequestParam Integer score,
            @RequestParam Double percentageScore) {
        return quizSubmissionService.submitQuiz(quizId, studentId, score, percentageScore);
    }

    @GetMapping("/quiz/{quizId}")
    public List<QuizSubmission> getQuizSubmissionsByQuizId(@PathVariable Long quizId) {
        return quizSubmissionService.getQuizSubmissionsByQuizId(quizId);
    }

    @GetMapping("/student/{studentId}")
    public List<QuizSubmission> getQuizSubmissionsByStudentId(@PathVariable Long studentId) {
        return quizSubmissionService.getQuizSubmissionsByStudentId(studentId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizSubmission(@PathVariable Long id) {
        quizSubmissionService.deleteQuizSubmission(id);
        return ResponseEntity.noContent().build();
    }
}