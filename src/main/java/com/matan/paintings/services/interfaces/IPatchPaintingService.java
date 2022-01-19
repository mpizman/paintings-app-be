package com.matan.paintings.services.interfaces;

import com.github.fge.jsonpatch.JsonPatch;
import com.matan.paintings.models.interfaces.IPaintingDTO;

import java.io.FileNotFoundException;

public interface IPatchPaintingService {
    IPaintingDTO execute(String id, JsonPatch patchElements) throws FileNotFoundException;
}
