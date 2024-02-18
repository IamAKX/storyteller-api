package com.storyteller.services;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.Author;
import com.storyteller.entities.User;
import com.storyteller.entities.UserFollowAuthor;
import com.storyteller.exceptions.EntityValidationException;
import com.storyteller.repositories.AuthorRepository;
import com.storyteller.repositories.UserFollowAuthorRepository;
import com.storyteller.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFollowAuthorService {

    @Autowired
    private UserFollowAuthorRepository userFollowAuthorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public ResponseData follow(Long userId, Long authorId) {

        Optional<UserFollowAuthor> existingFollow = userFollowAuthorRepository.findByUserIdAndAuthorId(userId, authorId);
        if (existingFollow.isPresent()) {
            throw new EntityValidationException("UserFollowAuthor", "User already follow this author");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("Author not found"));

        UserFollowAuthor userFollowAuthor = new UserFollowAuthor();
        userFollowAuthor.setUser(user);
        userFollowAuthor.setAuthor(author);

        try {
            UserFollowAuthor insertedFollow = userFollowAuthorRepository.save(userFollowAuthor);
            return ResponseData.builder()
                    .message("Followed")
                    .statusCode(HttpStatus.OK.value())
                    .data(insertedFollow)
                    .build();
        } catch (ConstraintViolationException exception) {
            throw new EntityValidationException("UserFollowAuthor", "User already follow this author");
        }
    }

    public ResponseData unfollow(Long userId, Long authorId) {
        Optional<UserFollowAuthor> existingFollow = userFollowAuthorRepository.findByUserIdAndAuthorId(userId, authorId);
        if (existingFollow.isPresent()) {
            userFollowAuthorRepository.delete(existingFollow.get());
            return ResponseData.builder()
                    .message("Unfollowed")
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } else {
            throw new EntityValidationException("UserFollowAuthor", "User already follow this author");
        }
    }

    public ResponseData getFollowedAuthorByUser(Long userId){
        List<UserFollowAuthor> followList = userFollowAuthorRepository.findByUserId(userId);
        List<Author> authorList = followList.stream().map(UserFollowAuthor::getAuthor).toList();
        return ResponseData.builder()
                .message("Followed Authors")
                .statusCode(HttpStatus.OK.value())
                .data(authorList)
                .additionalData(authorList.size())
                .build();
    }

    public ResponseData getFollowingUserByAuthor(Long authorId){
        List<UserFollowAuthor> followList = userFollowAuthorRepository.findByAuthorId(authorId);
        List<User> userList = followList.stream().map(UserFollowAuthor::getUser).toList();
        return ResponseData.builder()
                .message("Following Users")
                .statusCode(HttpStatus.OK.value())
                .data(userList)
                .additionalData(userList.size())
                .build();
    }
}
