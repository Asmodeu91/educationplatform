package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CourseReviewTest {

    @Test
    public void testCourseReviewCreation() {
        // Given
        Course course = new Course();
        User student = new User();
        Integer rating = 5;
        String comment = "Excellent course!";

        // When
        CourseReview review = new CourseReview(course, student, rating, comment);

        // Then
        assertEquals(course, review.getCourse());
        assertEquals(student, review.getStudent());
        assertEquals(rating, review.getRating());
        assertEquals(comment, review.getComment());
        assertNull(review.getCreatedAt());
        assertNull(review.getId());
    }

    @Test
    public void testCourseReviewSettersAndGetters() {
        // Given
        CourseReview review = new CourseReview();
        Course course = new Course();
        User student = new User();
        LocalDateTime now = LocalDateTime.now();

        // When
        review.setId(1L);
        review.setCourse(course);
        review.setStudent(student);
        review.setRating(4);
        review.setComment("Very good course");
        review.setCreatedAt(now);

        // Then
        assertEquals(1L, review.getId());
        assertEquals(course, review.getCourse());
        assertEquals(student, review.getStudent());
        assertEquals(4, review.getRating());
        assertEquals("Very good course", review.getComment());
        assertEquals(now, review.getCreatedAt());
    }
}