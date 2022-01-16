package com.matan.paintings.services.mappers.interfaces;

import com.matan.paintings.models.interfaces.ISortDTO;

public interface ISortInputToSortDTO {
    public ISortDTO map(String sortOrder, String sortField);
}
