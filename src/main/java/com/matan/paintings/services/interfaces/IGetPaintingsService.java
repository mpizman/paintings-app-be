package com.matan.paintings.services.interfaces;

import com.matan.paintings.models.interfaces.IMiniPaintingDTO;
import com.matan.paintings.models.interfaces.IPaginationDTO;
import com.matan.paintings.models.interfaces.ISortDTO;
import org.springframework.data.domain.Page;

public interface IGetPaintingsService {

    Page<IMiniPaintingDTO> execute(String searchQuery, ISortDTO sortDTO, IPaginationDTO paginationDTO);
}
