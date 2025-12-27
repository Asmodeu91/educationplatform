package com.educationplatform.repository;

import com.educationplatform.model.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {
    List<CourseReview> findByCourseId(Long courseId);
    List<CourseReview> findByStudentId(Long studentId);
    Optional<CourseReview> findByStudentIdAndCourseId(Long studentId, Long courseId);
}