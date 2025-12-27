package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class QuizSubmissionTest {

    @Test
    public void testQuizSubmissionCreation() {
        // Given
        Quiz quiz = new Quiz();
        User student = new User();
        Integer score = 85;
        Double percentageScore = 85.0;

        // When
        QuizSubmission submission = new QuizSubmission(quiz, student, score, percentageScore);

        // Then
        assertEquals(quiz, submission.getQuiz());
        assertEquals(student, submission.getStudent());
        assertEquals(score, submission.getScore());
        assertEquals(percentageScore, submission.getPercentageScore());
        assertNotNull(submission.getTakenAt());
        assertNull(submission.getTimeSpent());
        assertNull(submission.getId());
    }

    @Test
    public void testQuizSubmissionSettersAndGetters() {
        // Given
        QuizSubmission submission = new QuizSubmission();
        Quiz quiz = new Quiz();
        User student = new User();
        LocalDateTime now = LocalDateTime.now();

        // When
        submission.setId(1L);
        submission.setQuiz(quiz);
        submission.setStudent(student);
        submission.setScore(90);
        submission.setPercentageScore(90.0);
        submission.setTakenAt(now);
        submission.setTimeSpent(1800);

        // Then
        assertEquals(1L, submission.getId());
        assertEquals(quiz, submission.getQuiz());
        assertEquals(student, submission.getStudent());
        assertEquals(90, submission.getScore());
        assertEquals(90.0, submission.getPercentageScore());
        assertEquals(now, submission.getTakenAt());
        assertEquals(1800, submission.getTimeSpent());
    }
}