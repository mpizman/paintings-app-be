package com.matan.paintings.services.implementations;

import com.matan.paintings.models.interfaces.IPaintingDTO;
import com.matan.paintings.repository.PaintingRepository;
import com.matan.paintings.services.interfaces.IGetPaintingByIdService;
import org.springframework.beans.factory.annotation.Autowired;

public class GetPaintingByIdService implements IGetPaintingByIdService {

    @Autowired
    PaintingRepository paintingRepository;

    @Override
    public IPaintingDTO execute(String id) {
        return paintingRepository.findById(id).orElse(null);
    }
}
