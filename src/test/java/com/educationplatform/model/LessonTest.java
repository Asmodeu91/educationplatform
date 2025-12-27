package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LessonTest {

    @Test
    public void testLessonCreation() {
        // Given
        String title = "Java Basics";
        Module module = new Module();

        // When
        Lesson lesson = new Lesson(title, module);

        // Then
        assertEquals(title, lesson.getTitle());
        assertEquals(module, lesson.getModule());
        assertNull(lesson.getId());
    }

    @Test
    public void testLessonSettersAndGetters() {
        // Given
        Lesson lesson = new Lesson();
        Module module = new Module();

        // When
        lesson.setId(1L);
        lesson.setTitle("OOP Concepts");
        lesson.setModule(module);
        lesson.setContent("Object-oriented programming principles");
        lesson.setVideoUrl("https://example.com/video1");
        lesson.setResourcesUrl("https://example.com/resources1");

        // Then
        assertEquals(1L, lesson.getId());
        assertEquals("OOP Concepts", lesson.getTitle());
        assertEquals(module, lesson.getModule());
        assertEquals("Object-oriented programming principles", lesson.getContent());
        assertEquals("https://example.com/video1", lesson.getVideoUrl());
        assertEquals("https://example.com/resources1", lesson.getResourcesUrl());
    }

    @Test
    public void testAddAndRemoveAssignment() {
        // Given
        Lesson lesson = new Lesson();
        Assignment assignment = new Assignment();

        // When
        lesson.addAssignment(assignment);

        // Then
        assertTrue(lesson.getAssignments().contains(assignment));
        assertEquals(lesson, assignment.getLesson());

        // When
        lesson.removeAssignment(assignment);

        // Then
        assertFalse(lesson.getAssignments().contains(assignment));
        assertNull(assignment.getLesson());
    }
}