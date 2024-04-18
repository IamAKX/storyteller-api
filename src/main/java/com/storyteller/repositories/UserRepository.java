package com.storyteller.repositories;

import com.storyteller.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMobile(String mobile);

    Optional<User> findById(Long id);
    Optional<User> findByFirebaseAuthId(String id);

    List<User> findAll();

}
