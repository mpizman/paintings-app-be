package com.matan.paintings.services.implementations;

import com.matan.paintings.models.implemenatations.PaintingDTO;
import com.matan.paintings.models.interfaces.IPaintingDTO;
import com.matan.paintings.repository.PaintingRepository;
import com.matan.paintings.services.interfaces.IPostPaintingService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostPaintingService implements IPostPaintingService {

    @Autowired
    PaintingRepository paintingRepository;

    public IPaintingDTO execute(IPaintingDTO painting) {
        return paintingRepository.insert((PaintingDTO) painting);
    }
}
