package com.matan.paintings.services.interfaces;

import com.matan.paintings.DTOs.interfaces.IPaintingDTO;

public interface IPostPaintingService {
    IPaintingDTO execute(IPaintingDTO painting);
}
