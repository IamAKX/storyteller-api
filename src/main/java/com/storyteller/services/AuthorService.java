package com.storyteller.services;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.Author;
import com.storyteller.exceptions.EntityValidationException;
import com.storyteller.repositories.AuthorRepository;
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
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public ResponseData saveAuthor(Author author) {
        Optional<Author> existingAuthor = authorRepository.findByName(author.getName());
        if (existingAuthor.isPresent()) {
            throw new EntityValidationException("Author", "This author is already created");
        }
        try {
            Author insertedauthor = authorRepository.save(author);
            return ResponseData.builder()
                    .message("Author created successfully")
                    .statusCode(HttpStatus.OK.value())
                    .data(insertedauthor)
                    .build();
        } catch (ConstraintViolationException exception) {
            throw new EntityValidationException("User", "This author is already created");
        }
    }

    public ResponseData getAuthorById(Long id) {
        Author existingAuthor = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        return ResponseData.builder()
                .message("Author found")
                .statusCode(HttpStatus.OK.value())
                .data(existingAuthor)
                .build();
    }

    public ResponseData getAuthorByName(String name) {
        Author existingAuthor = authorRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        return ResponseData.builder()
                .message("Author found")
                .statusCode(HttpStatus.OK.value())
                .data(existingAuthor)
                .build();
    }

    public ResponseData updateAuthor(Long id, Author updatedAuthor){
        Author existingAuthor = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        BeanUtils.copyProperties(updatedAuthor, existingAuthor, ServiceHelper.getNullPropertyNames(updatedAuthor));
        Author insertedAuthor = authorRepository.save(existingAuthor);
        return ResponseData.builder()
                .message("Author updated successfully")
                .statusCode(HttpStatus.OK.value())
                .data(insertedAuthor)
                .build();
    }

    public ResponseData deleteAuthor(Long id){
        authorRepository.deleteById(id);
        return ResponseData.builder()
                .message("Author deleted successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseData getAllCategories() {
        List<Author> authorList = authorRepository.findAll();
        return ResponseData.builder()
                .message("Fetched all author")
                .statusCode(HttpStatus.OK.value())
                .data(authorList)
                .build();
    }
}
