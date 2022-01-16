package com.matan.paintings.models.implemenatations;

import com.matan.paintings.models.interfaces.ISortDTO;
import org.springframework.data.domain.Sort;

public class SortDTO implements ISortDTO {
    private String field;
    private Sort.Direction order;

    @Override
    public String getField() {
        return field;
    }

    @Override
    public void setField(String field) {
        this.field = field;
    }

    @Override
    public Sort.Direction getOrder() {
        return order;
    }

    @Override
    public void setOrder(Sort.Direction order) {
        this.order = order;
    }
}
