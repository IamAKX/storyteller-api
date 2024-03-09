package com.storyteller.repositories;

import com.storyteller.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
    List<Author> findByNameContaining(String name);

    Optional<Author> findById(Long id);

    List<Author> findAll();
}
