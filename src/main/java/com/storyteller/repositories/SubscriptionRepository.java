package com.storyteller.repositories;

import com.storyteller.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByTitleContaining(String name);

    Optional<Subscription> findByTitle(String name);

    Optional<Subscription> findById(Long id);

    List<Subscription> findAll();
}
