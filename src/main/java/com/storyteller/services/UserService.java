package com.storyteller.services;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.User;
import com.storyteller.exceptions.EntityValidationException;
import com.storyteller.repositories.UserRepository;
import com.storyteller.utilities.ServiceHelper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseData saveUser(User user) {
        Optional<User> existingUser = userRepository.findByMobile(user.getMobile());
        if (existingUser.isPresent()) {
            throw new EntityValidationException("User", "Mobile number already registered");
        }
        try {
            User insertedUser = userRepository.save(user);
            return ResponseData.builder()
                    .message("User created successfully")
                    .statusCode(HttpStatus.OK.value())
                    .data(insertedUser)
                    .build();
        } catch (ConstraintViolationException exception) {
            throw new EntityValidationException("User", "Mobile number already registered");
        }
    }

    public ResponseData getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return ResponseData.builder()
                .message("User found")
                .statusCode(HttpStatus.OK.value())
                .data(existingUser)
                .build();
    }

    public ResponseData getUserByMobileNumber(String mobile) {
        User existingUser = userRepository.findByMobile(mobile).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return ResponseData.builder()
                .message("User found")
                .statusCode(HttpStatus.OK.value())
                .data(existingUser)
                .build();
    }

    public ResponseData updateUser(Long id, User updatedUser){
        User existingUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        BeanUtils.copyProperties(updatedUser, existingUser, ServiceHelper.getNullPropertyNames(updatedUser));
        User insertedUser = userRepository.save(existingUser);
        return ResponseData.builder()
                .message("User updated successfully")
                .statusCode(HttpStatus.OK.value())
                .data(insertedUser)
                .build();
    }

    public ResponseData deleteUser(Long id){
        userRepository.deleteById(id);
        return ResponseData.builder()
                .message("User deleted successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseData getAllUsers() {
        List<User> userList = userRepository.findAll();
        return ResponseData.builder()
                .message("Fetched all users")
                .statusCode(HttpStatus.OK.value())
                .data(userList)
                .build();
    }
}
