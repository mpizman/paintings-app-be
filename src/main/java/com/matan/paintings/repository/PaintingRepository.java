package com.matan.paintings.repository;

import com.matan.paintings.models.implemenatations.PaintingDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaintingRepository extends MongoRepository<PaintingDTO,String> {
    //    Optional<PaintingDTO> findPaintingByName(String name);
    //    Optional<List<PaintingDTO>> findPaintingDTOByName(String name);
//        Optional<List<PaintingDTO>> find (String name);
}
