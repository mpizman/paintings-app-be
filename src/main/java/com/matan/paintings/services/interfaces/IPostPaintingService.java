package com.matan.paintings.services.interfaces;

import com.matan.paintings.models.interfaces.IPaintingDTO;

public interface IPostPaintingService {
    IPaintingDTO execute(IPaintingDTO painting);
}
