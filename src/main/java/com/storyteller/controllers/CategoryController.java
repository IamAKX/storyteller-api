package com.storyteller.controllers;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.Category;
import com.storyteller.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    private ResponseEntity<ResponseData> createCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.OK);
    }

    @GetMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping(value = { "/name/{name}" })
    private ResponseEntity<ResponseData> getCategoryByName(@PathVariable String name){
        return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
    }

    @PutMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> updateCategory(@PathVariable Long id, @RequestBody Category category){
        return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
    }

    @DeleteMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> deleteCategory(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<ResponseData> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
}
