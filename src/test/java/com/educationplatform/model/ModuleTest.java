package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModuleTest {

    @Test
    public void testModuleCreation() {
        // Given
        String title = "Introduction to Java";
        Course course = new Course();

        // When
        Module module = new Module(title, course);

        // Then
        assertEquals(title, module.getTitle());
        assertEquals(course, module.getCourse());
        assertNull(module.getId());
    }

    @Test
    public void testModuleSettersAndGetters() {
        // Given
        Module module = new Module();
        Course course = new Course();

        // When
        module.setId(1L);
        module.setTitle("Advanced Topics");
        module.setCourse(course);
        module.setOrderIndex(2);
        module.setDescription("Deep dive into advanced concepts");

        // Then
        assertEquals(1L, module.getId());
        assertEquals("Advanced Topics", module.getTitle());
        assertEquals(course, module.getCourse());
        assertEquals(2, module.getOrderIndex());
        assertEquals("Deep dive into advanced concepts", module.getDescription());
    }

    @Test
    public void testAddAndRemoveLesson() {
        // Given
        Module module = new Module();
        Lesson lesson = new Lesson();

        // When
        module.addLesson(lesson);

        // Then
        assertTrue(module.getLessons().contains(lesson));
        assertEquals(module, lesson.getModule());

        // When
        module.removeLesson(lesson);

        // Then
        assertFalse(module.getLessons().contains(lesson));
        assertNull(lesson.getModule());
    }

    @Test
    public void testSetAndUnsetQuiz() {
        // Given
        Module module = new Module();
        Quiz quiz = new Quiz();

        // When
        module.setQuiz(quiz);

        // Then
        assertEquals(quiz, module.getQuiz());
        assertEquals(module, quiz.getModule());

        // When
        // Сначала нужно очистить связь в quiz, потом в module
        quiz.setModule(null);
        module.setQuiz(null);

        // Then
        assertNull(module.getQuiz());
        assertNull(quiz.getModule());
    }
}