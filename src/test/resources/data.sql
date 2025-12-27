-- Clear existing data (in reverse order of dependencies)
DELETE FROM quiz_submissions;
DELETE FROM answer_options;
DELETE FROM questions;
DELETE FROM quizzes;
DELETE FROM submissions;
DELETE FROM assignments;
DELETE FROM lessons;
DELETE FROM modules;
DELETE FROM course_reviews;
DELETE FROM enrollments;
DELETE FROM course_tag;
DELETE FROM tags;
DELETE FROM courses;
DELETE FROM categories;
DELETE FROM profiles;
DELETE FROM users;

-- Insert Categories
INSERT INTO categories (id, name) VALUES
                                      (1, 'Programming'),
                                      (2, 'Web Development'),
                                      (3, 'Data Science'),
                                      (4, 'Mobile Development'),
                                      (5, 'DevOps'),
                                      (6, 'Software Engineering');

-- Insert Users
INSERT INTO users (id, name, email, role, created_at) VALUES
-- Teachers
(1, 'Dr. Alice Johnson', 'alice.johnson@university.edu', 'TEACHER', '2024-01-15 09:00:00'),
(2, 'Prof. Bob Smith', 'bob.smith@university.edu', 'TEACHER', '2024-01-16 10:00:00'),
(3, 'Dr. Carol Williams', 'carol.williams@university.edu', 'TEACHER', '2024-01-17 11:00:00'),

-- Students
(4, 'David Brown', 'david.brown@student.edu', 'STUDENT', '2024-01-18 12:00:00'),
(5, 'Emma Davis', 'emma.davis@student.edu', 'STUDENT', '2024-01-19 13:00:00'),
(6, 'Frank Miller', 'frank.miller@student.edu', 'STUDENT', '2024-01-20 14:00:00'),
(7, 'Grace Wilson', 'grace.wilson@student.edu', 'STUDENT', '2024-01-21 15:00:00'),
(8, 'Henry Moore', 'henry.moore@student.edu', 'STUDENT', '2024-01-22 16:00:00'),
(9, 'Ivy Taylor', 'ivy.taylor@student.edu', 'STUDENT', '2024-01-23 17:00:00'),
(10, 'Jack Anderson', 'jack.anderson@student.edu', 'STUDENT', '2024-01-24 18:00:00');

-- Insert Profiles
INSERT INTO profiles (id, user_id, bio, avatar_url, phone_number, date_of_birth) VALUES
                                                                                     (1, 1, 'Senior Professor of Computer Science with 15 years of experience', 'https://example.com/avatars/alice.jpg', '+1-555-0101', '1978-03-15'),
                                                                                     (2, 2, 'Expert in Web Development and Databases', 'https://example.com/avatars/bob.jpg', '+1-555-0102', '1982-07-22'),
                                                                                     (3, 3, 'PhD in Software Engineering, specializing in Agile methodologies', 'https://example.com/avatars/carol.jpg', '+1-555-0103', '1975-11-30'),
                                                                                     (4, 4, 'Computer Science student passionate about AI and Machine Learning', 'https://example.com/avatars/david.jpg', '+1-555-0104', '2000-05-10'),
                                                                                     (5, 5, 'Web Development enthusiast, learning full-stack technologies', 'https://example.com/avatars/emma.jpg', '+1-555-0105', '2001-02-28'),
                                                                                     (6, 6, 'Aspiring software engineer interested in backend development', 'https://example.com/avatars/frank.jpg', '+1-555-0106', '1999-09-15'),
                                                                                     (7, 7, 'UI/UX designer learning frontend development', 'https://example.com/avatars/grace.jpg', '+1-555-0107', '2000-12-05'),
                                                                                     (8, 8, 'Data Science student focusing on Python and statistics', 'https://example.com/avatars/henry.jpg', '+1-555-0108', '1998-08-20'),
                                                                                     (9, 9, 'Mobile app developer learning React Native', 'https://example.com/avatars/ivy.jpg', '+1-555-0109', '2001-04-18'),
                                                                                     (10, 10, 'DevOps enthusiast interested in cloud technologies', 'https://example.com/avatars/jack.jpg', '+1-555-0110', '1999-11-25');

-- Insert Tags
INSERT INTO tags (id, name) VALUES
                                (1, 'Java'),
                                (2, 'Spring Boot'),
                                (3, 'Python'),
                                (4, 'JavaScript'),
                                (5, 'React'),
                                (6, 'Database'),
                                (7, 'Docker'),
                                (8, 'Kubernetes'),
                                (9, 'AWS'),
                                (10, 'Machine Learning');

-- Insert Courses
INSERT INTO courses (id, title, description, category_id, teacher_id, start_date, duration, created_at) VALUES
                                                                                                            (1, 'Java Programming Fundamentals', 'Learn Java from scratch with hands-on projects', 1, 1, '2024-02-01 09:00:00', 8, '2024-01-25 10:00:00'),
                                                                                                            (2, 'Advanced Spring Boot', 'Master Spring Boot for enterprise applications', 1, 1, '2024-02-15 10:00:00', 10, '2024-01-26 11:00:00'),
                                                                                                            (3, 'Full-Stack Web Development', 'Build modern web applications with React and Node.js', 2, 2, '2024-03-01 11:00:00', 12, '2024-01-27 12:00:00'),
                                                                                                            (4, 'Python for Data Science', 'Data analysis and machine learning with Python', 3, 3, '2024-02-20 13:00:00', 10, '2024-01-28 13:00:00'),
                                                                                                            (5, 'React Native Mobile Development', 'Build cross-platform mobile apps with React Native', 4, 2, '2024-03-10 14:00:00', 8, '2024-01-29 14:00:00'),
                                                                                                            (6, 'Docker and Kubernetes Essentials', 'Containerization and orchestration for DevOps', 5, 1, '2024-02-25 15:00:00', 6, '2024-01-30 15:00:00'),
                                                                                                            (7, 'Software Architecture & Design Patterns', 'Design scalable and maintainable systems', 6, 3, '2024-03-05 16:00:00', 10, '2024-01-31 16:00:00');

-- Link Courses with Tags
INSERT INTO course_tag (course_id, tag_id) VALUES
                                               (1, 1), (1, 6),
                                               (2, 1), (2, 2), (2, 6),
                                               (3, 4), (3, 5), (3, 6),
                                               (4, 3), (4, 10),
                                               (5, 4), (5, 5),
                                               (6, 7), (6, 8), (6, 9),
                                               (7, 1), (7, 3), (7, 6);

-- Insert Modules for Course 1
INSERT INTO modules (id, title, order_index, description, course_id) VALUES
                                                                         (1, 'Introduction to Java', 1, 'Basics of Java programming language', 1),
                                                                         (2, 'Object-Oriented Programming', 2, 'Classes, objects, inheritance, polymorphism', 1),
                                                                         (3, 'Collections and Generics', 3, 'Working with Java collections framework', 1);

-- Insert Lessons for Module 1
INSERT INTO lessons (id, title, content, video_url, resources_url, module_id) VALUES
                                                                                  (1, 'Java Syntax Basics', 'Learn about variables, data types, and operators in Java', 'https://example.com/videos/java-syntax.mp4', 'https://example.com/resources/java-basics.pdf', 1),
                                                                                  (2, 'Control Flow Statements', 'If-else, switch, loops, and branching statements', 'https://example.com/videos/control-flow.mp4', 'https://example.com/resources/control-flow.pdf', 1),
                                                                                  (3, 'Methods and Functions', 'Defining and calling methods, parameters, return values', 'https://example.com/videos/methods.mp4', 'https://example.com/resources/methods.pdf', 1);

-- Insert Assignments
INSERT INTO assignments (id, title, description, due_date, max_score, lesson_id) VALUES
                                                                                     (1, 'Basic Calculator', 'Create a calculator that performs basic arithmetic operations', '2024-02-15 23:59:59', 100, 1),
                                                                                     (2, 'Grade Calculator', 'Write a program that calculates student grades based on scores', '2024-02-20 23:59:59', 100, 2),
                                                                                     (3, 'Temperature Converter', 'Convert between Celsius and Fahrenheit', '2024-02-25 23:59:59', 100, 3);

-- Insert Quizzes
INSERT INTO quizzes (id, title, time_limit, course_id, module_id) VALUES
                                                                      (1, 'Java Basics Quiz', 30, 1, 1),
                                                                      (2, 'OOP Concepts Quiz', 45, 1, 2),
                                                                      (3, 'Web Development Fundamentals', 60, 3, NULL);

-- Insert Questions for Quiz 1
INSERT INTO questions (id, text, type, quiz_id) VALUES
                                                    (1, 'What is the size of int data type in Java?', 'SINGLE_CHOICE', 1),
                                                    (2, 'Which of these are Java keywords?', 'MULTIPLE_CHOICE', 1),
                                                    (3, 'Java is a purely object-oriented language.', 'TRUE_FALSE', 1);

-- Insert Answer Options
INSERT INTO answer_options (id, text, is_correct, question_id) VALUES
-- Question 1 options
(1, '16 bits', FALSE, 1),
(2, '32 bits', TRUE, 1),
(3, '64 bits', FALSE, 1),
(4, 'Depends on platform', FALSE, 1),

-- Question 2 options
(5, 'class', TRUE, 2),
(6, 'struct', FALSE, 2),
(7, 'interface', TRUE, 2),
(8, 'extends', TRUE, 2),

-- Question 3 options
(9, 'True', FALSE, 3),
(10, 'False', TRUE, 3);

-- Insert Enrollments
INSERT INTO enrollments (id, user_id, course_id, status, enroll_date) VALUES
-- Course 1 enrollments
(1, 4, 1, 'ACTIVE', '2024-01-26 09:00:00'),
(2, 5, 1, 'ACTIVE', '2024-01-26 10:00:00'),
(3, 6, 1, 'ACTIVE', '2024-01-26 11:00:00'),
(4, 7, 1, 'ACTIVE', '2024-01-27 12:00:00'),

-- Course 2 enrollments
(5, 4, 2, 'ACTIVE', '2024-01-28 13:00:00'),
(6, 8, 2, 'ACTIVE', '2024-01-28 14:00:00'),

-- Course 3 enrollments
(7, 5, 3, 'ACTIVE', '2024-01-29 15:00:00'),
(8, 9, 3, 'ACTIVE', '2024-01-29 16:00:00'),
(9, 10, 3, 'ACTIVE', '2024-01-30 17:00:00'),

-- Course 4 enrollments
(10, 6, 4, 'ACTIVE', '2024-01-31 18:00:00'),
(11, 8, 4, 'ACTIVE', '2024-02-01 19:00:00');

-- Insert Submissions
INSERT INTO submissions (id, submitted_at, content, score, feedback, graded_at, assignment_id, student_id) VALUES
                                                                                                               (1, '2024-02-10 14:30:00', 'Implemented calculator with +, -, *, / operations', 95, 'Good implementation, consider adding error handling', '2024-02-11 10:00:00', 1, 4),
                                                                                                               (2, '2024-02-12 16:45:00', 'Grade calculator with letter grades A-F', 88, 'Missing support for weighted grades', '2024-02-13 11:30:00', 2, 4),
                                                                                                               (3, '2024-02-08 11:20:00', 'Temperature converter with bidirectional conversion', 92, 'Well structured code', '2024-02-09 09:15:00', 3, 5);

-- Insert Quiz Submissions
INSERT INTO quiz_submissions (id, taken_at, score, percentage_score, time_spent, quiz_id, student_id) VALUES
                                                                                                          (1, '2024-02-05 10:30:00', 28, 93.3, 1200, 1, 4),
                                                                                                          (2, '2024-02-06 14:15:00', 25, 83.3, 1500, 1, 5),
                                                                                                          (3, '2024-02-07 16:45:00', 30, 100.0, 1000, 1, 6);

-- Insert Course Reviews
INSERT INTO course_reviews (id, rating, comment, created_at, course_id, student_id) VALUES
                                                                                        (1, 5, 'Excellent course! The instructor explains concepts clearly.', '2024-02-20 10:00:00', 1, 4),
                                                                                        (2, 4, 'Good content, but could use more practical examples.', '2024-02-21 14:30:00', 1, 5),
                                                                                        (3, 5, 'Perfect for beginners. Highly recommended!', '2024-02-22 16:45:00', 3, 5);
