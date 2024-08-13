package com.example.demo.test.service;

import com.example.demo.test.entity.Author;
import com.example.demo.test.entity.Course;
import com.example.demo.test.repository.AuthorRepository;
import com.example.demo.test.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CourseRepository courseRepository;
//Saved Author
    public Author saveAuthorAndCourse(Author author) {
        // Save Author with basic details
        Author savedAuthor = new Author();
        savedAuthor.setName(author.getName());

        // Handle Course List
        List<Course> courseList = new ArrayList<>();
        for (Course course : author.getCourseList()) {
            Course existingCourse = courseRepository.findById(course.getId()).orElse(null);

            if (existingCourse == null) {
                // Create a new Course if it does not exist
                existingCourse = new Course();
                existingCourse.setCourseName(course.getCourseName());
                courseRepository.save(existingCourse);
            }

            courseList.add(existingCourse);
        }

        savedAuthor.setCourseList(courseList);
        return authorRepository.save(savedAuthor);
    }
}
