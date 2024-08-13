package com.example.demo.test.controller;

import com.example.demo.test.entity.User;
import com.example.demo.test.entity.UserProfile;
import com.example.demo.test.service.UserService;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
try{
    User user1=userService.saveUserDetails(user);
    return new ResponseEntity<>("create User succesfully", HttpStatus.CREATED);

}catch (Exception e){
    return new ResponseEntity<>("Failed to create employee", HttpStatus.INTERNAL_SERVER_ERROR);
}
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long id) {
        try {
            UserProfile userProfile = userService.getUserProfile(id);
            return ResponseEntity.ok(userProfile);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    }
