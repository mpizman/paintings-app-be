package com.matan.paintings.services.interfaces;

import java.io.FileNotFoundException;

public interface IDeletePaintingService {
    String deletePaintingService(String id) throws FileNotFoundException;
}
