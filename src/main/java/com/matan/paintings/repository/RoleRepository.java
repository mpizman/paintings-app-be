package com.matan.paintings.repository;

import com.matan.paintings.models.implemenatations.ERole;
import com.matan.paintings.models.implemenatations.RoleDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<RoleDTO, String> {
    Optional<RoleDTO> findByName(ERole name);
}
