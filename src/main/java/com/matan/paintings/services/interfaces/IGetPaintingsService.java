package com.matan.paintings.services.interfaces;

import com.matan.paintings.DTOs.implemenatations.PaintingDTO;

import java.util.List;

public interface IGetPaintingsService {

    List<PaintingDTO> execute(String searchQuery, int pageNumber, int rpp);
}
