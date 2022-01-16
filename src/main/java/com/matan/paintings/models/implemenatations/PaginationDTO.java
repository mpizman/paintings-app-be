package com.matan.paintings.models.implemenatations;

import com.matan.paintings.models.interfaces.IPaginationDTO;

public class PaginationDTO implements IPaginationDTO {
    private int rpp;
    private int pageNumber;
    private int totalCount;

    public int getRpp() {
        return rpp;
    }

    public void setRpp(int rpp) {
        this.rpp = rpp;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
