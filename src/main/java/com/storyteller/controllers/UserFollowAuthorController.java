package com.storyteller.controllers;

import com.storyteller.dto.ResponseData;
import com.storyteller.models.UserAuthorModel;
import com.storyteller.services.UserFollowAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/userFollowAuthor")
public class UserFollowAuthorController {

    @Autowired
    private UserFollowAuthorService userFollowAuthorService;

    @PostMapping( value = { "/follow" })
    private ResponseEntity<ResponseData> follow(@RequestBody UserAuthorModel userAuthor){
        return new ResponseEntity<>(userFollowAuthorService.follow(userAuthor.getUserId(), userAuthor.getAuthorId()), HttpStatus.OK);
    }

    @DeleteMapping( value = { "/unfollow" })
    private ResponseEntity<ResponseData> unfollow(@RequestBody UserAuthorModel userAuthor){
        return new ResponseEntity<>(userFollowAuthorService.unfollow(userAuthor.getUserId(), userAuthor.getAuthorId()), HttpStatus.OK);
    }

    @GetMapping( value = { "/user/{id}" })
    private ResponseEntity<ResponseData> getAuthorByUserId(@PathVariable Long id){
        return new ResponseEntity<>(userFollowAuthorService.getFollowedAuthorByUser(id), HttpStatus.OK);
    }

    @GetMapping( value = { "/author/{id}" })
    private ResponseEntity<ResponseData> getUserByAuthorId(@PathVariable Long id){
        return new ResponseEntity<>(userFollowAuthorService.getFollowingUserByAuthor(id), HttpStatus.OK);
    }
}
