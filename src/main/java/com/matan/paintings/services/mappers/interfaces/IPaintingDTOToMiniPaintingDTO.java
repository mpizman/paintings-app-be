package com.matan.paintings.services.mappers.interfaces;

import com.matan.paintings.models.interfaces.IMiniPaintingDTO;
import com.matan.paintings.models.interfaces.IPaintingDTO;

public interface IPaintingDTOToMiniPaintingDTO {
    IMiniPaintingDTO map(IPaintingDTO paintingDTO);
}
