package com.matan.paintings.controllers.implementations;

import com.github.fge.jsonpatch.JsonPatch;
import com.matan.paintings.models.interfaces.IPaginationDTO;
import com.matan.paintings.models.interfaces.ISortDTO;
import com.matan.paintings.controllers.interfaces.IPaintingsHandleController;
import com.matan.paintings.models.implemenatations.PaintingDTO;
import com.matan.paintings.models.interfaces.IPaintingDTO;
import com.matan.paintings.services.interfaces.*;
import com.matan.paintings.services.mappers.interfaces.IPaginationInputToPaginationDTO;
import com.matan.paintings.services.mappers.interfaces.ISortInputToSortDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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

    @Autowired
    IPatchPaintingService patchPaintingService;

    @Autowired
    IDeletePaintingService deletePaintingService;

    @Override
    @GetMapping("api/paintings")
    public ResponseEntity<?> getPaintings(@RequestParam Optional<String> searchQuery,
                                          @RequestParam Optional<String> uploaderUsername,
                                          @RequestParam Optional<String> artist,
                                          @RequestParam Optional<String> sortField,
                                          @RequestParam Optional<String> sortOrder,
                                          @RequestParam Optional<Integer> pageNumber,
                                          @RequestParam Optional<Integer> rpp) {

        ISortDTO sortDTO = sortInputToSortDTO.map(sortOrder.orElse("dec"), sortField.orElse(""));
        IPaginationDTO paginationDTO = paginationInputToPaginationDTO.map(pageNumber.orElse(0), rpp.orElse(10));

        try {
            return ResponseEntity.ok().body(getPaintingsService.execute(searchQuery.orElse(""),
                    uploaderUsername.orElse(""),
                    artist.orElse(""),
                    sortDTO,
                    paginationDTO));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
        }
    }

    @Override
    @PostMapping(path = "api/painting",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IPaintingDTO> postPainting(@RequestBody PaintingDTO painting) {
        return ResponseEntity.ok().body(postPaintingService.execute(painting));
    }

    @Override
    @GetMapping("api/painting/{id}")
    public ResponseEntity<IPaintingDTO> getPainting(@PathVariable(value = "id") String id) {
        IPaintingDTO painting = getPaintingByIdService.execute(id);
        if (painting != null) {
            return ResponseEntity.ok().body(painting);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @PatchMapping(path = "api/painting/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchPainting(@PathVariable(value = "id") String id, @RequestBody JsonPatch patchElements) {
        try {
            return ResponseEntity.ok().body(patchPaintingService.execute(id, patchElements));
        } catch (FileNotFoundException fileNotFoundException) {
            return ResponseEntity.notFound().build();
        } catch (InternalError internalError) {
            return ResponseEntity.internalServerError().build();
        } catch (InsufficientAuthenticationException insufficientAuthenticationException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (RuntimeException runtimeException) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(runtimeException.getMessage());
        }
    }

    @Override
    @DeleteMapping(path = "/api//painting/{id}")
    public ResponseEntity<?> deletePainting(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(deletePaintingService.deletePaintingService(id));
        } catch (FileNotFoundException fileNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("painting not found");
        } catch (InsufficientAuthenticationException insufficientAuthenticationException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(insufficientAuthenticationException.getMessage());
        }
    }
}
