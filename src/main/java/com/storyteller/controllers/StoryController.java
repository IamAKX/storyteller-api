package com.storyteller.controllers;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.Story;
import com.storyteller.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/story")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @PostMapping
    private ResponseEntity<ResponseData> createStory(@RequestBody Story story) {
        return new ResponseEntity<>(storyService.saveStory(story), HttpStatus.OK);
    }

    @GetMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> getStoryById(@PathVariable Long id){
        return new ResponseEntity<>(storyService.getStoryById(id), HttpStatus.OK);
    }

    @GetMapping(value = { "/name/{name}" })
    private ResponseEntity<ResponseData> getStoryByName(@PathVariable String name){
        return new ResponseEntity<>(storyService.getStoryByName(name), HttpStatus.OK);
    }

    @GetMapping(value = { "/author/{name}" })
    private ResponseEntity<ResponseData> getStoryByAuthor(@PathVariable String name){
        return new ResponseEntity<>(storyService.getStoryByAuthor(name), HttpStatus.OK);
    }

    @GetMapping(value = { "/tag/{tag}" })
    private ResponseEntity<ResponseData> getStoryByTag(@PathVariable String tag){
        return new ResponseEntity<>(storyService.getStoryByTags(tag), HttpStatus.OK);
    }

    @PutMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> updateStory(@PathVariable Long id, @RequestBody Story story){
        return new ResponseEntity<>(storyService.updateStory(id, story), HttpStatus.OK);
    }

    @DeleteMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> deleteStory(@PathVariable Long id){
        return new ResponseEntity<>(storyService.deleteStory(id), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<ResponseData> getAllStory(){
        return new ResponseEntity<>(storyService.getAllStory(), HttpStatus.OK);
    }
}
