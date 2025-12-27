package com.educationplatform.service;

import com.educationplatform.model.Question;
import com.educationplatform.model.Quiz;
import com.educationplatform.repository.QuestionRepository;
import com.educationplatform.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Question createQuestion(Question question) {
        if (question.getQuiz() != null && question.getQuiz().getId() != null) {
            Quiz quiz = quizRepository.findById(question.getQuiz().getId())
                    .orElseThrow(() -> new RuntimeException("Quiz not found"));
            question.setQuiz(quiz);
        }
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question questionDetails) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id " + id));
        question.setText(questionDetails.getText());
        question.setType(questionDetails.getType());

        if (questionDetails.getQuiz() != null && questionDetails.getQuiz().getId() != null) {
            Quiz quiz = quizRepository.findById(questionDetails.getQuiz().getId())
                    .orElseThrow(() -> new RuntimeException("Quiz not found"));
            question.setQuiz(quiz);
        }

        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id " + id));
        questionRepository.delete(question);
    }

    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}