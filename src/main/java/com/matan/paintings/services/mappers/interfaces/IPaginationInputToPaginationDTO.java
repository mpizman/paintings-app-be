package com.matan.paintings.services.mappers.interfaces;

import com.matan.paintings.DTOs.interfaces.IPaginationDTO;

public interface IPaginationInputToPaginationDTO {

    IPaginationDTO map(int pageNumber, int rpp);
}
