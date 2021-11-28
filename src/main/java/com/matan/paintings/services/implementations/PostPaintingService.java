package com.matan.paintings.services.implementations;

import com.matan.paintings.painting.Painting;
import com.matan.paintings.services.interfaces.IPostPaintingService;

public class PostPaintingService implements IPostPaintingService {
    public Painting execute(Painting painting) {
        return painting;
    }
}
