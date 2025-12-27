package com.educationplatform.service;

import com.educationplatform.model.Submission;
import com.educationplatform.model.User;
import com.educationplatform.model.Assignment;
import com.educationplatform.repository.SubmissionRepository;
import com.educationplatform.repository.UserRepository;
import com.educationplatform.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }

    public Submission submitAssignment(Long assignmentId, Long studentId, String content) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Submission> existing = submissionRepository.findByStudentIdAndAssignmentId(studentId, assignmentId);
        if (!existing.isEmpty()) {
            throw new RuntimeException("Submission already exists for this assignment");
        }

        Submission submission = new Submission(assignment, student, content);
        return submissionRepository.save(submission);
    }

    public List<Submission> getSubmissionsByAssignmentId(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }

    public List<Submission> getSubmissionsByStudentId(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    public Submission gradeSubmission(Long id, Integer score, String feedback) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
        submission.setScore(score);
        submission.setFeedback(feedback);
        return submissionRepository.save(submission);
    }

    public void deleteSubmission(Long id) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found with id " + id));
        submissionRepository.delete(submission);
    }
}