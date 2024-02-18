package com.storyteller.repositories;

import com.storyteller.entities.UserFollowAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFollowAuthorRepository extends JpaRepository<UserFollowAuthor, Long> {

    Optional<UserFollowAuthor> findByUserIdAndAuthorId(Long userId, Long authorId);

    List<UserFollowAuthor> findByUserId(Long userId);

    List<UserFollowAuthor> findByAuthorId(Long authorId);

}
