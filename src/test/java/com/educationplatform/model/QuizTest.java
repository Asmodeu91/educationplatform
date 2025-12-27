package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    @Test
    public void testQuizCreationWithCourse() {
        // Given
        String title = "Java Basics Quiz";
        Course course = new Course();

        // When
        Quiz quiz = new Quiz(title, course);

        // Then
        assertEquals(title, quiz.getTitle());
        assertEquals(course, quiz.getCourse());
        assertNull(quiz.getModule());
        assertNull(quiz.getId());
    }

    @Test
    public void testQuizCreationWithModule() {
        // Given
        String title = "Module 1 Quiz";
        Module module = new Module();

        // When
        Quiz quiz = new Quiz(title, module);

        // Then
        assertEquals(title, quiz.getTitle());
        assertEquals(module, quiz.getModule());
        assertNull(quiz.getCourse());
        assertNull(quiz.getId());
    }

    @Test
    public void testQuizSettersAndGetters() {
        // Given
        Quiz quiz = new Quiz();
        Course course = new Course();
        Module module = new Module();

        // When
        quiz.setId(1L);
        quiz.setTitle("Advanced Quiz");
        quiz.setCourse(course);
        quiz.setModule(module);
        quiz.setTimeLimit(60);

        // Then
        assertEquals(1L, quiz.getId());
        assertEquals("Advanced Quiz", quiz.getTitle());
        assertEquals(course, quiz.getCourse());
        assertEquals(module, quiz.getModule());
        assertEquals(60, quiz.getTimeLimit());
    }

    @Test
    public void testAddAndRemoveQuestion() {
        // Given
        Quiz quiz = new Quiz();
        Question question = new Question();

        // When
        quiz.addQuestion(question);

        // Then
        assertTrue(quiz.getQuestions().contains(question));
        assertEquals(quiz, question.getQuiz());

        // When
        quiz.removeQuestion(question);

        // Then
        assertFalse(quiz.getQuestions().contains(question));
        assertNull(question.getQuiz());
    }
}