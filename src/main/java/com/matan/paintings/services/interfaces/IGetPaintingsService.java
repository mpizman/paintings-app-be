package com.matan.paintings.services.interfaces;

import com.matan.paintings.DTOs.implemenatations.PaintingDTO;
import com.matan.paintings.DTOs.interfaces.IPaginationDTO;
import com.matan.paintings.DTOs.interfaces.ISortDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IGetPaintingsService {

    Page<PaintingDTO> execute(String searchQuery, ISortDTO sortDTO, IPaginationDTO paginationDTO);
}
