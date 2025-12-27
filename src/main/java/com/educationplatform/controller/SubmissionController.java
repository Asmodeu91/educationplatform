package com.educationplatform.controller;

import com.educationplatform.model.Submission;
import com.educationplatform.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id) {
        return submissionService.getSubmissionById(id)
                .map(submission -> ResponseEntity.ok().body(submission))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/submit")
    public Submission submitAssignment(
            @RequestParam Long assignmentId,
            @RequestParam Long studentId,
            @RequestBody String content) {
        return submissionService.submitAssignment(assignmentId, studentId, content);
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<Submission> getSubmissionsByAssignmentId(@PathVariable Long assignmentId) {
        return submissionService.getSubmissionsByAssignmentId(assignmentId);
    }

    @GetMapping("/student/{studentId}")
    public List<Submission> getSubmissionsByStudentId(@PathVariable Long studentId) {
        return submissionService.getSubmissionsByStudentId(studentId);
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<Submission> gradeSubmission(
            @PathVariable Long id,
            @RequestParam Integer score,
            @RequestParam String feedback) {
        Submission gradedSubmission = submissionService.gradeSubmission(id, score, feedback);
        return ResponseEntity.ok(gradedSubmission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.noContent().build();
    }
}