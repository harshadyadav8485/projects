package com.example.demo.test.service;

import com.example.demo.test.entity.User;
import com.example.demo.test.entity.UserProfile;
import com.example.demo.test.repository.UserProfileRepository;
import com.example.demo.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    public User saveUserDetails(User user) {
        try {
            if (user == null || user.getName() == null || user.getUserProfile() == null || user.getUserProfile().getAddress() == null) {
            }

            // Create a new User object and set the name
            User u = new User();
            u.setName(user.getName());
            userRepository.save(u);

            // Create a new UserProfile object and set the address and the user reference
            UserProfile userProfile = new UserProfile();
            userProfile.setAddress(user.getUserProfile().getAddress());
            userProfile.setUser(u);

            // Save the UserProfile object first to generate its ID
            userProfileRepository.save(userProfile);

            // Set the UserProfile reference in the User object
            u.setUserProfile(userProfile);

            // Save the User object
            userRepository.save(u);

            return u;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user", e); // Re-throw the exception to let the caller handle it
        }
    }

    public UserProfile getUserProfile(Long id){
Optional<UserProfile> userProfile=userProfileRepository.findById(id);
        
return userProfile.get();
    }

}
