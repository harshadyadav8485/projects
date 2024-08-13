package com.example.demo.test.controller;

import com.example.demo.test.entity.Author;
import com.example.demo.test.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
//Crud Operation
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping
    private ResponseEntity<String> saveAuthorAndCourse(@RequestBody Author author){
try{
    Author author1=authorService.saveAuthorAndCourse(author);
return new ResponseEntity<>("create Author succesfully", HttpStatus.CREATED);
}catch (Exception e){
    return new ResponseEntity<>("Failed to create Author", HttpStatus.INTERNAL_SERVER_ERROR);

}
    }


}
