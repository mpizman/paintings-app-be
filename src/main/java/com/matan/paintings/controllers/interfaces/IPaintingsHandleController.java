package com.matan.paintings.controllers.interfaces;

import com.matan.paintings.DTOs.implemenatations.PaginationDTO;
import com.matan.paintings.DTOs.implemenatations.PaintingDTO;
import com.matan.paintings.DTOs.implemenatations.SortDTO;
import com.matan.paintings.DTOs.interfaces.IPaginationDTO;
import com.matan.paintings.DTOs.interfaces.IPaintingDTO;
import com.matan.paintings.DTOs.interfaces.ISortDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface IPaintingsHandleController {

    Page<PaintingDTO> getPaintings(@RequestParam Optional<String> searchQuery,
                                   @RequestParam Optional<String> sortField,
                                   @RequestParam Optional<String> sortOrder,
                                   @RequestParam Optional<Integer> pageNumber,
                                   @RequestParam Optional<Integer> rpp);

    IPaintingDTO postPainting(@RequestBody PaintingDTO painting);
}
