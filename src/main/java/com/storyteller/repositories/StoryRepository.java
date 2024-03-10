package com.storyteller.repositories;

import com.storyteller.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    Optional<Story> findByName(String name);

    List<Story> findByNameContaining(String name);
    List<Story> findByTagsContaining(String tag);

    Optional<Story> findById(Long id);

    List<Story> findAll();
}
