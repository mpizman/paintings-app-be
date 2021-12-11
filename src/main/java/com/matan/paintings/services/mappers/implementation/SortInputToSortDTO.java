package com.matan.paintings.services.mappers.implementation;

import com.matan.paintings.DTOs.implemenatations.SortDTO;
import com.matan.paintings.DTOs.interfaces.ISortDTO;
import com.matan.paintings.services.mappers.interfaces.ISortInputToSortDTO;

import javax.inject.Inject;
import javax.inject.Provider;

public class SortInputToSortDTO implements ISortInputToSortDTO {

    @Inject
    private Provider<SortDTO> sortDTOProvider;

    public ISortDTO map(String sortOrder, String sortField) {
        ISortDTO sortDTO = sortDTOProvider.get();
        sortDTO.setField(sortField);
        sortDTO.setOrder(sortOrder);
        return sortDTO;
    }
}
