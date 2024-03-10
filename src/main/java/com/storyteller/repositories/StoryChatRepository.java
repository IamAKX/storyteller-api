package com.storyteller.repositories;

import com.storyteller.entities.StoryChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryChatRepository extends JpaRepository<StoryChat, Long> {

    Optional<StoryChat> findById(Long id);

    List<StoryChat> findByStoryIdOrderBySerialNumber(Long id);

    @Query("SELECT MAX(s.serialNumber) FROM StoryChat s WHERE s.story.id = :storyId")
    Optional<Long> findMaxSerialNumberByStoryId(Long storyId);

}
