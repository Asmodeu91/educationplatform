package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {

    @Test
    public void testAssignmentCreation() {
        // Given
        String title = "Java Homework";
        String description = "Complete the exercises";
        Lesson lesson = new Lesson();

        // When
        Assignment assignment = new Assignment(title, description, lesson);

        // Then
        assertEquals(title, assignment.getTitle());
        assertEquals(description, assignment.getDescription());
        assertEquals(lesson, assignment.getLesson());
        assertNull(assignment.getId());
    }

    @Test
    public void testAssignmentSettersAndGetters() {
        // Given
        Assignment assignment = new Assignment();
        Lesson lesson = new Lesson();
        LocalDateTime dueDate = LocalDateTime.now().plusDays(7);

        // When
        assignment.setId(1L);
        assignment.setTitle("Advanced Assignment");
        assignment.setDescription("Complex programming tasks");
        assignment.setLesson(lesson);
        assignment.setDueDate(dueDate);
        assignment.setMaxScore(100);

        // Then
        assertEquals(1L, assignment.getId());
        assertEquals("Advanced Assignment", assignment.getTitle());
        assertEquals("Complex programming tasks", assignment.getDescription());
        assertEquals(lesson, assignment.getLesson());
        assertEquals(dueDate, assignment.getDueDate());
        assertEquals(100, assignment.getMaxScore());
    }

    @Test
    public void testAddAndRemoveSubmission() {
        // Given
        Assignment assignment = new Assignment();
        Submission submission = new Submission();

        // When
        assignment.addSubmission(submission);

        // Then
        assertTrue(assignment.getSubmissions().contains(submission));
        assertEquals(assignment, submission.getAssignment());

        // When
        assignment.removeSubmission(submission);

        // Then
        assertFalse(assignment.getSubmissions().contains(submission));
        assertNull(submission.getAssignment());
    }
}