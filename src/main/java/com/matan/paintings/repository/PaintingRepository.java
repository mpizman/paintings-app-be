package com.matan.paintings.repository;

import com.matan.paintings.painting.Painting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaintingRepository extends MongoRepository<Painting,String> {
    Optional<Painting> findPaintingByName(String name);
}
