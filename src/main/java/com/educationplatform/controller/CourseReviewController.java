package com.educationplatform.controller;

import com.educationplatform.model.CourseReview;
import com.educationplatform.service.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course-reviews")
public class CourseReviewController {

    @Autowired
    private CourseReviewService courseReviewService;

    @GetMapping
    public List<CourseReview> getAllCourseReviews() {
        return courseReviewService.getAllCourseReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReview> getCourseReviewById(@PathVariable Long id) {
        return courseReviewService.getCourseReviewById(id)
                .map(review -> ResponseEntity.ok().body(review))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CourseReview> createCourseReview(@RequestBody Map<String, Object> request) {
        Long courseId = Long.valueOf(request.get("courseId").toString());
        Long studentId = Long.valueOf(request.get("studentId").toString());
        Integer rating = Integer.valueOf(request.get("rating").toString());
        String comment = request.get("comment").toString();

        CourseReview createdReview = courseReviewService.createCourseReview(courseId, studentId, rating, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseReview> getCourseReviewsByCourseId(@PathVariable Long courseId) {
        return courseReviewService.getCourseReviewsByCourseId(courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<CourseReview> getCourseReviewsByStudentId(@PathVariable Long studentId) {
        return courseReviewService.getCourseReviewsByStudentId(studentId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseReview(@PathVariable Long id) {
        courseReviewService.deleteCourseReview(id);
        return ResponseEntity.noContent().build();
    }
}