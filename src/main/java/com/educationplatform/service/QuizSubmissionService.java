package com.educationplatform.service;

import com.educationplatform.model.QuizSubmission;
import com.educationplatform.model.User;
import com.educationplatform.model.Quiz;
import com.educationplatform.repository.QuizSubmissionRepository;
import com.educationplatform.repository.UserRepository;
import com.educationplatform.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizSubmissionService {

    @Autowired
    private QuizSubmissionRepository quizSubmissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    public List<QuizSubmission> getAllQuizSubmissions() {
        return quizSubmissionRepository.findAll();
    }

    public Optional<QuizSubmission> getQuizSubmissionById(Long id) {
        return quizSubmissionRepository.findById(id);
    }

    public QuizSubmission submitQuiz(Long quizId, Long studentId, Integer score, Double percentageScore) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Optional<QuizSubmission> existing = quizSubmissionRepository.findByStudentIdAndQuizId(studentId, quizId);
        if (existing.isPresent()) {
            throw new RuntimeException("Quiz already submitted");
        }

        QuizSubmission submission = new QuizSubmission(quiz, student, score, percentageScore);
        return quizSubmissionRepository.save(submission);
    }

    public List<QuizSubmission> getQuizSubmissionsByQuizId(Long quizId) {
        return quizSubmissionRepository.findByQuizId(quizId);
    }

    public List<QuizSubmission> getQuizSubmissionsByStudentId(Long studentId) {
        return quizSubmissionRepository.findByStudentId(studentId);
    }

    public void deleteQuizSubmission(Long id) {
        QuizSubmission submission = quizSubmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz submission not found with id " + id));
        quizSubmissionRepository.delete(submission);
    }
}