package com.storyteller.controllers;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.User;
import com.storyteller.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    private ResponseEntity<ResponseData> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @GetMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping(value = { "/mobile/{mobile}" })
    private ResponseEntity<ResponseData> getUserByMobileNumber(@PathVariable String mobile){
        return new ResponseEntity<>(userService.getUserByMobileNumber(mobile), HttpStatus.OK);
    }

    @PutMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> updateUser(@PathVariable Long id, @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<ResponseData> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
