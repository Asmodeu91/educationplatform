package com.educationplatform.service;

import com.educationplatform.model.Assignment;
import com.educationplatform.model.Lesson;
import com.educationplatform.repository.AssignmentRepository;
import com.educationplatform.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    public Assignment createAssignment(Assignment assignment) {
        if (assignment.getLesson() != null && assignment.getLesson().getId() != null) {
            Lesson lesson = lessonRepository.findById(assignment.getLesson().getId())
                    .orElseThrow(() -> new RuntimeException("Lesson not found"));
            assignment.setLesson(lesson);
        }
        return assignmentRepository.save(assignment);
    }

    public Assignment updateAssignment(Long id, Assignment assignmentDetails) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id " + id));
        assignment.setTitle(assignmentDetails.getTitle());
        assignment.setDescription(assignmentDetails.getDescription());
        assignment.setDueDate(assignmentDetails.getDueDate());
        assignment.setMaxScore(assignmentDetails.getMaxScore());

        if (assignmentDetails.getLesson() != null && assignmentDetails.getLesson().getId() != null) {
            Lesson lesson = lessonRepository.findById(assignmentDetails.getLesson().getId())
                    .orElseThrow(() -> new RuntimeException("Lesson not found"));
            assignment.setLesson(lesson);
        }

        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id " + id));
        assignmentRepository.delete(assignment);
    }

    public List<Assignment> getAssignmentsByLessonId(Long lessonId) {
        return assignmentRepository.findByLessonId(lessonId);
    }
}