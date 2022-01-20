package com.matan.paintings.services.mappers.implementation;

import com.matan.paintings.models.interfaces.IMiniPaintingDTO;
import com.matan.paintings.models.interfaces.IPaintingDTO;
import com.matan.paintings.services.mappers.interfaces.IPaintingDTOToMiniPaintingDTO;

import javax.inject.Inject;
import javax.inject.Provider;

public class PaintingDTOToMiniPaintingDTO implements IPaintingDTOToMiniPaintingDTO {

    @Inject
    private Provider<IMiniPaintingDTO> miniPaintingDTOProvider;

    @Override
    public IMiniPaintingDTO map(IPaintingDTO paintingDTO) {
        IMiniPaintingDTO miniPaintingDTO = miniPaintingDTOProvider.get();
        miniPaintingDTO.setId(paintingDTO.getId());
        miniPaintingDTO.setArtist(paintingDTO.getArtist());
        miniPaintingDTO.setName(paintingDTO.getName());
        miniPaintingDTO.setDescription(paintingDTO.getDescription());
        miniPaintingDTO.setPrice(paintingDTO.getPrice());
        return miniPaintingDTO;
    }
}
