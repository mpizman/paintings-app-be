package com.matan.paintings.repository;

import com.matan.paintings.painting.implemenatations.PaintingDTO;
import com.matan.paintings.painting.interfaces.IPaintingDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaintingRepository extends MongoRepository<PaintingDTO,String> {
    Optional<PaintingDTO> findPaintingByName(String name);
}
