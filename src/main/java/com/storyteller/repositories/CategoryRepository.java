package com.storyteller.repositories;

import com.storyteller.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findByNameContaining(String name);

    Optional<Category> findById(Long id);

    List<Category> findAll();
}
