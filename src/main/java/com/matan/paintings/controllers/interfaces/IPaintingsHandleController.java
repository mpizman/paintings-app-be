package com.matan.paintings.controllers.interfaces;

import com.matan.paintings.models.implemenatations.PaintingDTO;
import com.matan.paintings.models.interfaces.IPaintingDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface IPaintingsHandleController {

    Page<PaintingDTO> getPaintings(@RequestParam Optional<String> searchQuery,
                                   @RequestParam Optional<String> sortField,
                                   @RequestParam Optional<String> sortOrder,
                                   @RequestParam Optional<Integer> pageNumber,
                                   @RequestParam Optional<Integer> rpp);

    IPaintingDTO postPainting(@RequestBody PaintingDTO painting);
}
