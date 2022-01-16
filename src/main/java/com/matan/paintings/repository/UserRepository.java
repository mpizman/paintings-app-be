package com.matan.paintings.repository;

import com.matan.paintings.models.implemenatations.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDTO, String> {
    Optional<UserDTO> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
