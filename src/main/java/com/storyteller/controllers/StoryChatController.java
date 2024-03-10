package com.storyteller.controllers;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.StoryChat;
import com.storyteller.services.StoryChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/storychat")
public class StoryChatController {

    @Autowired
    private StoryChatService storyChatService;

    @GetMapping(value = { "/story/{id}" })
    private ResponseEntity<ResponseData> getChatStoryByStoryId(@PathVariable Long id){
        return new ResponseEntity<>(storyChatService.getStoryChatByStoryId(id), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<ResponseData> createChatStory(@RequestBody StoryChat storyChat) {
        return new ResponseEntity<>(storyChatService.saveStoryChat(storyChat), HttpStatus.OK);
    }

    @PutMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> updateChatStory(@PathVariable Long id, @RequestBody StoryChat storyChat){
        return new ResponseEntity<>(storyChatService.updateChatStory(id, storyChat), HttpStatus.OK);
    }

    @DeleteMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> deleteStory(@PathVariable Long id){
        return new ResponseEntity<>(storyChatService.deleteChatStory(id), HttpStatus.OK);
    }
}
