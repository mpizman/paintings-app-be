package com.matan.paintings.controllers.implementations;

import com.matan.paintings.controllers.interfaces.IPaintingsHandleController;
import com.matan.paintings.painting.implemenatations.PaintingDTO;
import com.matan.paintings.painting.interfaces.IPaintingDTO;
import com.matan.paintings.repository.PaintingRepository;
import com.matan.paintings.services.interfaces.IGetPaintingByIdService;
import com.matan.paintings.services.interfaces.IPostPaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaintingsHandleController implements IPaintingsHandleController {

    @Autowired
    IPostPaintingService postPaintingService;

    @Autowired
    IGetPaintingByIdService getPaintingByIdService;

    @GetMapping("/paintings")
    public String getPaintings() {
        return "Paintings";
    }

    @PostMapping(path = "/painting",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public IPaintingDTO postPainting(@RequestBody PaintingDTO painting) {
        return postPaintingService.execute(painting);
    }

    @GetMapping("/painting/{id}")
    public IPaintingDTO getPainting(@PathVariable(value="id") String id) {
        return getPaintingByIdService.execute(id);
    }

}
