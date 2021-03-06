package com.matan.paintings.controllers.interfaces;

import com.github.fge.jsonpatch.JsonPatch;
import com.matan.paintings.models.implemenatations.PaintingDTO;
import com.matan.paintings.models.interfaces.IPaintingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface IPaintingsHandleController {

    ResponseEntity<?> getPaintings(@RequestParam Optional<String> searchQuery,
                                   @RequestParam Optional<String> uploaderUsername,
                                   @RequestParam Optional<String> artist,
                                   @RequestParam Optional<String> name,
                                   @RequestParam Optional<String> sortField,
                                   @RequestParam Optional<String> sortOrder,
                                   @RequestParam Optional<Integer> pageNumber,
                                   @RequestParam Optional<Integer> rpp);

    ResponseEntity<?> postPainting(@RequestBody PaintingDTO painting);

    ResponseEntity<IPaintingDTO> getPainting(@PathVariable(value = "id") String id);

    ResponseEntity<?> patchPainting(@PathVariable(value = "id") String id, @RequestBody JsonPatch patchElements);

    ResponseEntity<?> deletePainting(@PathVariable(value = "id") String id);
}
