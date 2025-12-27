package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Test
    public void testCourseCreation() {
        // Given
        String title = "Java Programming";
        String description = "Learn Java from scratch";
        Category category = new Category();
        User teacher = new User();

        // When
        Course course = new Course(title, description, category, teacher);

        // Then
        assertEquals(title, course.getTitle());
        assertEquals(description, course.getDescription());
        assertEquals(category, course.getCategory());
        assertEquals(teacher, course.getTeacher());
        assertNull(course.getId());
        assertNull(course.getCreatedAt());
        assertNull(course.getUpdatedAt());
    }

    @Test
    public void testCourseSettersAndGetters() {
        // Given
        Course course = new Course();
        LocalDateTime now = LocalDateTime.now();
        Category category = new Category();
        User teacher = new User();

        // When
        course.setId(1L);
        course.setTitle("Advanced Java");
        course.setDescription("Deep dive into Java");
        course.setCategory(category);
        course.setTeacher(teacher);
        course.setStartDate(now);
        course.setDuration(8);
        course.setCreatedAt(now);
        course.setUpdatedAt(now);

        // Then
        assertEquals(1L, course.getId());
        assertEquals("Advanced Java", course.getTitle());
        assertEquals("Deep dive into Java", course.getDescription());
        assertEquals(category, course.getCategory());
        assertEquals(teacher, course.getTeacher());
        assertEquals(now, course.getStartDate());
        assertEquals(8, course.getDuration());
        assertEquals(now, course.getCreatedAt());
        assertEquals(now, course.getUpdatedAt());
    }

    @Test
    public void testAddAndRemoveModule() {
        // Given
        Course course = new Course();
        Module module = new Module();

        // When
        course.addModule(module);

        // Then
        assertTrue(course.getModules().contains(module));
        assertEquals(course, module.getCourse());

        // When
        course.removeModule(module);

        // Then
        assertFalse(course.getModules().contains(module));
        assertNull(module.getCourse());
    }
}