package com.matan.paintings.services.implementations;

import com.matan.paintings.DTOs.implemenatations.PaintingDTO;
import com.matan.paintings.repository.PaintingRepository;
import com.matan.paintings.services.interfaces.IGetPaintingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import java.util.List;

public class GetPaintingsService implements IGetPaintingsService {

    @Autowired
    PaintingRepository paintingRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<PaintingDTO> execute(String searchQuery, int pageNumber, int rpp) {
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchQuery);
        return mongoTemplate.find(TextQuery.queryText(textCriteria).sortByScore().with(PageRequest.of(pageNumber,rpp)), PaintingDTO.class);
    }
}
