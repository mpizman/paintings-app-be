package com.matan.paintings.models.interfaces;

public interface IPaginationDTO {
    int getRpp();

    void setRpp(int rpp);

    int getPageNumber();

    void setPageNumber(int pageNumber);

    int getTotalCount();

    void setTotalCount(int totalCount);
}
