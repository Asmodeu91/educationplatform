package com.educationplatform.service;

import com.educationplatform.model.CourseReview;
import com.educationplatform.model.User;
import com.educationplatform.model.Course;
import com.educationplatform.repository.CourseReviewRepository;
import com.educationplatform.repository.UserRepository;
import com.educationplatform.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseReviewService {

    @Autowired
    private CourseReviewRepository courseReviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseReview> getAllCourseReviews() {
        return courseReviewRepository.findAll();
    }

    public Optional<CourseReview> getCourseReviewById(Long id) {
        return courseReviewRepository.findById(id);
    }

    public CourseReview createCourseReview(Long courseId, Long studentId, Integer rating, String comment) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Optional<CourseReview> existing = courseReviewRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (existing.isPresent()) {
            throw new RuntimeException("Review already exists for this course");
        }

        CourseReview review = new CourseReview(course, student, rating, comment);
        return courseReviewRepository.save(review);
    }

    public List<CourseReview> getCourseReviewsByCourseId(Long courseId) {
        return courseReviewRepository.findByCourseId(courseId);
    }

    public List<CourseReview> getCourseReviewsByStudentId(Long studentId) {
        return courseReviewRepository.findByStudentId(studentId);
    }

    public void deleteCourseReview(Long id) {
        CourseReview review = courseReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course review not found with id " + id));
        courseReviewRepository.delete(review);
    }
}