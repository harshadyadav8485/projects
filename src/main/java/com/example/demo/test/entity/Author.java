package com.example.demo.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name="author_course",
            joinColumns =@JoinColumn (name="author_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")

    )
    private List<Course> courseList;
}
