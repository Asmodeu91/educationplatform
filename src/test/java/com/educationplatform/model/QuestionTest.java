package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    @Test
    public void testQuestionCreation() {
        // Given
        String text = "What is Java?";
        Question.QuestionType type = Question.QuestionType.SINGLE_CHOICE;
        Quiz quiz = new Quiz();

        // When
        Question question = new Question(text, type, quiz);

        // Then
        assertEquals(text, question.getText());
        assertEquals(type, question.getType());
        assertEquals(quiz, question.getQuiz());
        assertNull(question.getId());
    }

    @Test
    public void testQuestionSettersAndGetters() {
        // Given
        Question question = new Question();
        Quiz quiz = new Quiz();

        // When
        question.setId(1L);
        question.setText("Advanced Java question");
        question.setQuiz(quiz);
        question.setType(Question.QuestionType.MULTIPLE_CHOICE);

        // Then
        assertEquals(1L, question.getId());
        assertEquals("Advanced Java question", question.getText());
        assertEquals(quiz, question.getQuiz());
        assertEquals(Question.QuestionType.MULTIPLE_CHOICE, question.getType());
    }

    @Test
    public void testAddAndRemoveOption() {
        // Given
        Question question = new Question();
        AnswerOption option = new AnswerOption();

        // When
        question.addOption(option);

        // Then
        assertTrue(question.getOptions().contains(option));
        assertEquals(question, option.getQuestion());

        // When
        question.removeOption(option);

        // Then
        assertFalse(question.getOptions().contains(option));
        assertNull(option.getQuestion());
    }

    @Test
    public void testQuestionTypeValues() {
        // When
        Question.QuestionType[] types = Question.QuestionType.values();

        // Then
        assertEquals(3, types.length);
        assertEquals(Question.QuestionType.SINGLE_CHOICE, types[0]);
        assertEquals(Question.QuestionType.MULTIPLE_CHOICE, types[1]);
        assertEquals(Question.QuestionType.TRUE_FALSE, types[2]);
    }
}