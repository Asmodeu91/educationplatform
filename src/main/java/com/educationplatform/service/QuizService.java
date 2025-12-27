package com.educationplatform.service;

import com.educationplatform.model.Quiz;
import com.educationplatform.model.Course;
import com.educationplatform.model.Module;
import com.educationplatform.repository.QuizRepository;
import com.educationplatform.repository.CourseRepository;
import com.educationplatform.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Quiz createQuiz(Quiz quiz) {
        if (quiz.getCourse() != null && quiz.getCourse().getId() != null) {
            Course course = courseRepository.findById(quiz.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            quiz.setCourse(course);
        }
        if (quiz.getModule() != null && quiz.getModule().getId() != null) {
            Module module = moduleRepository.findById(quiz.getModule().getId())
                    .orElseThrow(() -> new RuntimeException("Module not found"));
            quiz.setModule(module);
        }
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Long id, Quiz quizDetails) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id " + id));
        quiz.setTitle(quizDetails.getTitle());
        quiz.setTimeLimit(quizDetails.getTimeLimit());

        if (quizDetails.getCourse() != null && quizDetails.getCourse().getId() != null) {
            Course course = courseRepository.findById(quizDetails.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            quiz.setCourse(course);
        }
        if (quizDetails.getModule() != null && quizDetails.getModule().getId() != null) {
            Module module = moduleRepository.findById(quizDetails.getModule().getId())
                    .orElseThrow(() -> new RuntimeException("Module not found"));
            quiz.setModule(module);
        }

        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id " + id));
        quizRepository.delete(quiz);
    }

    public List<Quiz> getQuizzesByCourseId(Long courseId) {
        return quizRepository.findByCourseId(courseId);
    }

    public List<Quiz> getQuizzesByModuleId(Long moduleId) {
        return quizRepository.findByModuleId(moduleId);
    }
}