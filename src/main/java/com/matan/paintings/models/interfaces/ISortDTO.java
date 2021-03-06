package com.matan.paintings.models.interfaces;

import org.springframework.data.domain.Sort;

public interface ISortDTO {
    String getField();

    void setField(String field);

    Sort.Direction getOrder();

    void setOrder(Sort.Direction order);
}
