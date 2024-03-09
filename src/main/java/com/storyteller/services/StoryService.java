package com.storyteller.services;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.Story;
import com.storyteller.exceptions.EntityValidationException;
import com.storyteller.repositories.StoryRepository;
import com.storyteller.utilities.ServiceHelper;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    public ResponseData saveStory(Story story) {
        try {
            Story insertedStory = storyRepository.save(story);
            return ResponseData.builder()
                    .message("Story created successfully")
                    .statusCode(HttpStatus.OK.value())
                    .data(insertedStory)
                    .build();
        } catch (ConstraintViolationException exception) {
            throw new EntityValidationException("Subscription", "This subscription is already created");
        }
    }

    public ResponseData getStoryById(Long id) {
        Story existingStory = storyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Story not found"));
        return ResponseData.builder()
                .message("Story found")
                .statusCode(HttpStatus.OK.value())
                .data(existingStory)
                .build();
    }

    public ResponseData getStoryByName(String name) {
        List<Story> existingStory = storyRepository.findByNameContaining(name);
        return ResponseData.builder()
                .message("Story found")
                .statusCode(HttpStatus.OK.value())
                .data(existingStory)
                .build();
    }

    public ResponseData getStoryByTags(String tag) {
        List<Story> existingStory = storyRepository.findByTagsContaining(tag);
        return ResponseData.builder()
                .message("Story found")
                .statusCode(HttpStatus.OK.value())
                .data(existingStory)
                .build();
    }

    public ResponseData updateStory(Long id, Story updatedStory){
        Story existingStory = storyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Story not found"));
        BeanUtils.copyProperties(updatedStory, existingStory, ServiceHelper.getNullPropertyNames(updatedStory));

        Story insertedStory = storyRepository.save(existingStory);
        return ResponseData.builder()
                .message("Story updated successfully")
                .statusCode(HttpStatus.OK.value())
                .data(insertedStory)
                .build();
    }

    public ResponseData deleteStory(Long id){
        storyRepository.deleteById(id);
        // TODO : Delete from story_chat as well
        return ResponseData.builder()
                .message("Story deleted successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseData getAllStory() {
        List<Story> storyList = storyRepository.findAll();
        return ResponseData.builder()
                .message("Fetched all Story")
                .statusCode(HttpStatus.OK.value())
                .data(storyList)
                .build();
    }
}
