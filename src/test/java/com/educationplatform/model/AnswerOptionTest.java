package com.educationplatform.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerOptionTest {

    @Test
    public void testAnswerOptionCreation() {
        // Given
        String text = "Java is a programming language";
        Boolean isCorrect = true;
        Question question = new Question();

        // When
        AnswerOption option = new AnswerOption(text, isCorrect, question);

        // Then
        assertEquals(text, option.getText());
        assertEquals(isCorrect, option.getIsCorrect());
        assertEquals(question, option.getQuestion());
        assertNull(option.getId());
    }

    @Test
    public void testAnswerOptionSettersAndGetters() {
        // Given
        AnswerOption option = new AnswerOption();
        Question question = new Question();

        // When
        option.setId(1L);
        option.setText("Python is a programming language");
        option.setIsCorrect(false);
        option.setQuestion(question);

        // Then
        assertEquals(1L, option.getId());
        assertEquals("Python is a programming language", option.getText());
        assertFalse(option.getIsCorrect());
        assertEquals(question, option.getQuestion());
    }

    @Test
    public void testDefaultConstructor() {
        // When
        AnswerOption option = new AnswerOption();

        // Then
        assertNull(option.getId());
        assertNull(option.getText());
        assertNull(option.getIsCorrect());
        assertNull(option.getQuestion());
    }
}