package com.matan.paintings.services.mappers.implementation;

import com.matan.paintings.models.interfaces.ISortDTO;
import com.matan.paintings.services.mappers.interfaces.ISortInputToSortDTO;
import org.springframework.data.domain.Sort;

import javax.inject.Inject;
import javax.inject.Provider;

public class SortInputToSortDTO implements ISortInputToSortDTO {

    @Inject
    private Provider<ISortDTO> sortDTOProvider;

    public ISortDTO map(String sortOrder, String sortField) {
        ISortDTO sortDTO = sortDTOProvider.get();
        sortDTO.setField(sortField);
        sortDTO.setOrder(mapOrder(sortOrder));
        return sortDTO;
    }

    private Sort.Direction mapOrder(String stringOrder) {
        if (stringOrder.equals("asc")) {
            return Sort.Direction.ASC;
        }
        else {
            return Sort.Direction.DESC;
        }
    }
}
