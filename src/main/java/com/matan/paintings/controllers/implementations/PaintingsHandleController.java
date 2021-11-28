package com.matan.paintings.controllers.implementations;

import com.matan.paintings.controllers.interfaces.IPaintingsHandleController;
import com.matan.paintings.painting.Painting;
import com.matan.paintings.repository.PaintingRepository;
import com.matan.paintings.services.interfaces.IPostPaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PaintingsHandleController implements IPaintingsHandleController {

    @Autowired
    IPostPaintingService postPaintingService;

    @Autowired
    PaintingRepository paintingRepository;

    @GetMapping("/paintings")
    public String getPaintings() {
        return "Paintings";
    }

    @PostMapping(path = "/painting",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Painting postPainting(@RequestBody Painting painting) {
//        Date date = new Date();
//        Painting painting1 = new Painting("Matan", "a", "s", date, "ss", 100);
        paintingRepository.insert(painting);
        return painting;
//        return postPaintingService.execute(painting);
    }

//    @GetMapping("/painting")

}
