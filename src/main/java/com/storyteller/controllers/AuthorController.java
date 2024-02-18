package com.storyteller.controllers;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.Author;
import com.storyteller.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    private ResponseEntity<ResponseData> createAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.saveAuthor(author), HttpStatus.OK);
    }

    @GetMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> getAuthorById(@PathVariable Long id){
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @GetMapping(value = { "/name/{name}" })
    private ResponseEntity<ResponseData> getAuthorByName(@PathVariable String name){
        return new ResponseEntity<>(authorService.getAuthorByName(name), HttpStatus.OK);
    }

    @PutMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> updateAuthor(@PathVariable Long id, @RequestBody Author author){
        return new ResponseEntity<>(authorService.updateAuthor(id, author), HttpStatus.OK);
    }

    @DeleteMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> deleteAuthor(@PathVariable Long id){
        return new ResponseEntity<>(authorService.deleteAuthor(id), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<ResponseData> getAllAuthor(){
        return new ResponseEntity<>(authorService.getAllCategories(), HttpStatus.OK);
    }
}
