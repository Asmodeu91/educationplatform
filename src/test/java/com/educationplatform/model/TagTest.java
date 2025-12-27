package com.educationplatform.model;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TagTest {

    @Test
    public void testTagCreation() {
        // Given
        String name = "Java";

        // When
        Tag tag = new Tag(name);

        // Then
        assertEquals(name, tag.getName());
        assertNull(tag.getId());
    }

    @Test
    public void testTagSettersAndGetters() {
        // Given
        Tag tag = new Tag();

        // When
        tag.setId(1L);
        tag.setName("Spring Framework");

        // Then
        assertEquals(1L, tag.getId());
        assertEquals("Spring Framework", tag.getName());
    }

    @Test
    public void testTagWithCourses() {
        // Given
        Tag tag = new Tag("Test Tag");
        Course course1 = new Course();
        Course course2 = new Course();

        // When
        List<Course> courses = Arrays.asList(course1, course2);
        tag.setCourses(courses);

        // Then
        assertEquals(2, tag.getCourses().size());
        assertTrue(tag.getCourses().contains(course1));
        assertTrue(tag.getCourses().contains(course2));
    }
}