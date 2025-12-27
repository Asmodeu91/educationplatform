package com.educationplatform.service;

import com.educationplatform.model.AnswerOption;
import com.educationplatform.model.Question;
import com.educationplatform.repository.AnswerOptionRepository;
import com.educationplatform.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerOptionService {

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<AnswerOption> getAllAnswerOptions() {
        return answerOptionRepository.findAll();
    }

    public Optional<AnswerOption> getAnswerOptionById(Long id) {
        return answerOptionRepository.findById(id);
    }

    public AnswerOption createAnswerOption(AnswerOption answerOption) {
        if (answerOption.getQuestion() != null && answerOption.getQuestion().getId() != null) {
            Question question = questionRepository.findById(answerOption.getQuestion().getId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));
            answerOption.setQuestion(question);
        }
        return answerOptionRepository.save(answerOption);
    }

    public AnswerOption updateAnswerOption(Long id, AnswerOption answerOptionDetails) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AnswerOption not found with id " + id));
        answerOption.setText(answerOptionDetails.getText());
        answerOption.setIsCorrect(answerOptionDetails.getIsCorrect());

        if (answerOptionDetails.getQuestion() != null && answerOptionDetails.getQuestion().getId() != null) {
            Question question = questionRepository.findById(answerOptionDetails.getQuestion().getId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));
            answerOption.setQuestion(question);
        }

        return answerOptionRepository.save(answerOption);
    }

    public void deleteAnswerOption(Long id) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AnswerOption not found with id " + id));
        answerOptionRepository.delete(answerOption);
    }

    public List<AnswerOption> getAnswerOptionsByQuestionId(Long questionId) {
        return answerOptionRepository.findByQuestionId(questionId);
    }

}