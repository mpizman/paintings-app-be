package com.matan.paintings.controllers.interfaces;

import com.matan.paintings.painting.Painting;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPaintingsHandleController {

    String getPaintings();
    Painting postPainting(@RequestBody Painting painting);
}
