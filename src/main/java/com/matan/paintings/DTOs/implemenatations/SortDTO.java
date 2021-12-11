package com.matan.paintings.DTOs.implemenatations;

import com.matan.paintings.DTOs.interfaces.ISortDTO;

public class SortDTO implements ISortDTO {
    private String field;
    private String order;

    @Override
    public String getField() {
        return field;
    }

    @Override
    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String getOrder() {
        return order;
    }

    @Override
    public void setOrder(String order) {
        this.order = order;
    }
}
