package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    public void testCategoryCreation() {
        // Given
        String name = "Programming";

        // When
        Category category = new Category(name);

        // Then
        assertEquals(name, category.getName());
        assertNull(category.getId());
    }

    @Test
    public void testCategorySettersAndGetters() {
        // Given
        Category category = new Category();

        // When
        category.setId(1L);
        category.setName("Web Development");

        // Then
        assertEquals(1L, category.getId());
        assertEquals("Web Development", category.getName());
    }

    @Test
    public void testCategoryWithCourses() {
        // Given
        Category category = new Category("Test Category");
        Course course1 = new Course();
        Course course2 = new Course();

        // When
        List<Course> courses = Arrays.asList(course1, course2);
        category.setCourses(courses);

        // Then
        assertEquals(2, category.getCourses().size());
        assertTrue(category.getCourses().contains(course1));
        assertTrue(category.getCourses().contains(course2));
    }
}