package com.matan.paintings.controllers.interfaces;

import com.matan.paintings.painting.implemenatations.PaintingDTO;
import com.matan.paintings.painting.interfaces.IPaintingDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPaintingsHandleController {

    String getPaintings();
    IPaintingDTO postPainting(@RequestBody PaintingDTO painting);
}
