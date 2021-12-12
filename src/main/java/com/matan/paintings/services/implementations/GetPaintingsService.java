package com.matan.paintings.services.implementations;

import com.matan.paintings.DTOs.implemenatations.PaintingDTO;
import com.matan.paintings.DTOs.interfaces.IPaginationDTO;
import com.matan.paintings.DTOs.interfaces.ISortDTO;
import com.matan.paintings.repository.PaintingRepository;
import com.matan.paintings.services.interfaces.IGetPaintingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import java.util.ArrayList;
import java.util.List;

public class GetPaintingsService implements IGetPaintingsService {

    @Autowired
    PaintingRepository paintingRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<PaintingDTO> execute(String searchQuery, ISortDTO sortDTO, IPaginationDTO paginationDTO) {
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchQuery);

        switch (sortDTO.getField()) {
            case "score":
                return mongoTemplate.find(TextQuery.queryText(textCriteria)
                        .sortByScore()
                        .with(PageRequest.of(paginationDTO.getPageNumber(),paginationDTO.getRpp())), PaintingDTO.class);
            case "date":
                return mongoTemplate.find(TextQuery.queryText(textCriteria)
                        .with(PageRequest.of(paginationDTO.getPageNumber(),paginationDTO.getRpp()))
                        .with(Sort.by(sortDTO.getOrder(), "date")), PaintingDTO.class);
            default:
                return new ArrayList<>();
        }
    }
}
