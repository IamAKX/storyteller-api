package com.storyteller.services;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.StoryChat;
import com.storyteller.exceptions.EntityValidationException;
import com.storyteller.repositories.StoryChatRepository;
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
public class StoryChatService {

    @Autowired
    private StoryChatRepository storyChatRepository;

    public ResponseData saveStoryChat(StoryChat storyChat) {
        try {
            Optional<Long> maxSerialNumber = storyChatRepository.findMaxSerialNumberByStoryId(storyChat.getStory().getId());
            if(maxSerialNumber.isPresent())
                storyChat.setSerialNumber(maxSerialNumber.get() + 1);
            else
                storyChat.setSerialNumber(0);
            StoryChat chat = storyChatRepository.save(storyChat);
            return ResponseData.builder()
                    .message("Story chat created successfully")
                    .statusCode(HttpStatus.OK.value())
                    .data(chat)
                    .build();
        } catch (ConstraintViolationException exception) {
            throw new EntityValidationException("Story Chat", "Failed to add story");
        }
    }

    public ResponseData getStoryChatByStoryId(Long id) {
        List<StoryChat> chats = storyChatRepository.findByStoryIdOrderBySerialNumber(id);
        return ResponseData.builder()
                .message("Chats fetched")
                .statusCode(HttpStatus.OK.value())
                .data(chats)
                .build();
    }

    public ResponseData updateChatStory(Long id, StoryChat storyChat){
        StoryChat existingStoryChat = storyChatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Story Chat not found"));
        BeanUtils.copyProperties(storyChat, existingStoryChat, ServiceHelper.getNullPropertyNames(storyChat));
        StoryChat insertedStoryChat = storyChatRepository.save(existingStoryChat);
        return ResponseData.builder()
                .message("Story Chat updated successfully")
                .statusCode(HttpStatus.OK.value())
                .data(insertedStoryChat)
                .build();
    }

    public ResponseData deleteChatStory(Long id){
        storyChatRepository.deleteById(id);
        return ResponseData.builder()
                .message("Chat Story deleted successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

}
