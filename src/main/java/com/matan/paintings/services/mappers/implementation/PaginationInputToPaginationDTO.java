package com.matan.paintings.services.mappers.implementation;

import com.matan.paintings.DTOs.interfaces.IPaginationDTO;
import com.matan.paintings.services.mappers.interfaces.IPaginationInputToPaginationDTO;

import javax.inject.Inject;
import javax.inject.Provider;

public class PaginationInputToPaginationDTO implements IPaginationInputToPaginationDTO {
    @Inject
    private Provider<IPaginationDTO> paginationDTOProvider;

    @Override
    public IPaginationDTO map(int pageNumber, int rpp) {
        IPaginationDTO paginationDTO = paginationDTOProvider.get();
        paginationDTO.setPageNumber(pageNumber);
        paginationDTO.setRpp(rpp);
        return paginationDTO;
    }
}
