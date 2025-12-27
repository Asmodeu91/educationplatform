package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class SubmissionTest {

    @Test
    public void testSubmissionCreation() {
        // Given
        Assignment assignment = new Assignment();
        User student = new User();
        String content = "Completed the assignment";

        // When
        Submission submission = new Submission(assignment, student, content);

        // Then
        assertEquals(assignment, submission.getAssignment());
        assertEquals(student, submission.getStudent());
        assertEquals(content, submission.getContent());
        assertNotNull(submission.getSubmittedAt());
        assertEquals(0, submission.getScore());
        assertNull(submission.getGradedAt());
        assertNull(submission.getId());
    }

    @Test
    public void testSubmissionSettersAndGetters() {
        // Given
        Submission submission = new Submission();
        Assignment assignment = new Assignment();
        User student = new User();
        LocalDateTime now = LocalDateTime.now();

        // When
        submission.setId(1L);
        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setSubmittedAt(now);
        submission.setContent("Updated submission");
        submission.setFilePath("/submissions/1/file.pdf");
        submission.setScore(95);
        submission.setFeedback("Great work!");

        // Then
        assertEquals(1L, submission.getId());
        assertEquals(assignment, submission.getAssignment());
        assertEquals(student, submission.getStudent());
        assertEquals(now, submission.getSubmittedAt());
        assertEquals("Updated submission", submission.getContent());
        assertEquals("/submissions/1/file.pdf", submission.getFilePath());
        assertEquals(95, submission.getScore());
        assertEquals("Great work!", submission.getFeedback());
        assertNotNull(submission.getGradedAt());
    }

    @Test
    public void testSetScoreSetsGradedAt() {
        // Given
        Submission submission = new Submission();

        // When
        submission.setScore(80);

        // Then
        assertEquals(80, submission.getScore());
        assertNotNull(submission.getGradedAt());
    }
}