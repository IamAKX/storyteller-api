package com.storyteller.services;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.Category;
import com.storyteller.exceptions.EntityValidationException;
import com.storyteller.repositories.CategoryRepository;
import com.storyteller.utilities.ServiceHelper;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseData saveCategory(Category category) {
        Optional<Category> existingCategory = categoryRepository.findByName(category.getName());
        if (existingCategory.isPresent()) {
            throw new EntityValidationException("Category", "This category is already created");
        }
        try {
            Category insertedcategory = categoryRepository.save(category);
            return ResponseData.builder()
                    .message("Category created successfully")
                    .statusCode(HttpStatus.OK.value())
                    .data(insertedcategory)
                    .build();
        } catch (ConstraintViolationException exception) {
            throw new EntityValidationException("User", "This category is already created");
        }
    }

    public ResponseData getCategoryById(Long id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return ResponseData.builder()
                .message("Category found")
                .statusCode(HttpStatus.OK.value())
                .data(existingCategory)
                .build();
    }

    public ResponseData getCategoryByName(String name) {
        Category existingCategory = categoryRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return ResponseData.builder()
                .message("Category found")
                .statusCode(HttpStatus.OK.value())
                .data(existingCategory)
                .build();
    }

    public ResponseData updateCategory(Long id, Category updatedCategory){
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        BeanUtils.copyProperties(updatedCategory, existingCategory, ServiceHelper.getNullPropertyNames(updatedCategory));
        Category insertedCategory = categoryRepository.save(existingCategory);
        return ResponseData.builder()
                .message("Category updated successfully")
                .statusCode(HttpStatus.OK.value())
                .data(insertedCategory)
                .build();
    }

    public ResponseData deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return ResponseData.builder()
                .message("Category deleted successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseData getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return ResponseData.builder()
                .message("Fetched all category")
                .statusCode(HttpStatus.OK.value())
                .data(categoryList)
                .build();
    }
}
