package com.matan.paintings.controllers.implementations;

import com.matan.paintings.DTOs.interfaces.IPaginationDTO;
import com.matan.paintings.DTOs.interfaces.ISortDTO;
import com.matan.paintings.controllers.interfaces.IPaintingsHandleController;
import com.matan.paintings.DTOs.implemenatations.PaintingDTO;
import com.matan.paintings.DTOs.interfaces.IPaintingDTO;
import com.matan.paintings.services.interfaces.IGetPaintingByIdService;
import com.matan.paintings.services.interfaces.IGetPaintingsService;
import com.matan.paintings.services.interfaces.IPostPaintingService;
import com.matan.paintings.services.mappers.interfaces.IPaginationInputToPaginationDTO;
import com.matan.paintings.services.mappers.interfaces.ISortInputToSortDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaintingsHandleController implements IPaintingsHandleController {

    @Autowired
    IPostPaintingService postPaintingService;

    @Autowired
    IGetPaintingByIdService getPaintingByIdService;

    @Autowired
    IGetPaintingsService getPaintingsService;

    @Autowired
    ISortInputToSortDTO sortInputToSortDTO;

    @Autowired
    IPaginationInputToPaginationDTO paginationInputToPaginationDTO;

    @Override
    @GetMapping("api/paintings")
    public List<PaintingDTO> getPaintings(@RequestParam Optional<String> searchQuery,
                                          @RequestParam Optional<String> sortField,
                                          @RequestParam Optional<String> sortOrder,
                                          @RequestParam Optional<Integer> pageNumber,
                                          @RequestParam Optional<Integer> rpp) {
        ISortDTO sortDTO = sortInputToSortDTO.map(sortOrder.orElse("dec"), sortField.orElse("score"));
        IPaginationDTO paginationDTO = paginationInputToPaginationDTO.map(pageNumber.orElse(0), rpp.orElse(10));
        return getPaintingsService.execute(searchQuery.orElse(""), sortDTO, paginationDTO);
    }

    @Override
    @PostMapping(path = "api/painting",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public IPaintingDTO postPainting(@RequestBody PaintingDTO painting) {
        return postPaintingService.execute(painting);
    }

    @GetMapping("api/painting/{id}")
    public IPaintingDTO getPainting(@PathVariable(value = "id") String id) {
        return getPaintingByIdService.execute(id);
    }

}
