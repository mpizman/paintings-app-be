package com.matan.paintings.services.implementations;

import com.matan.paintings.DTOs.implemenatations.PaintingDTO;
import com.matan.paintings.DTOs.interfaces.IPaginationDTO;
import com.matan.paintings.DTOs.interfaces.ISortDTO;
import com.matan.paintings.repository.PaintingRepository;
import com.matan.paintings.services.interfaces.IGetPaintingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import java.util.List;

public class GetPaintingsService implements IGetPaintingsService {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    PaintingRepository paintingRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Page<PaintingDTO> execute(String searchQuery, ISortDTO sortDTO, IPaginationDTO paginationDTO) {
        List<PaintingDTO> paintingDTOList;
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchQuery);
        Sort sort = Sort.by(sortDTO.getOrder(), "score");
        TextQuery textQuery = TextQuery.queryText(textCriteria);

        if ("date".equals(sortDTO.getField())) {
            sort = Sort.by(sortDTO.getOrder(), "date");
        }

        Pageable pageable = PageRequest.of(paginationDTO.getPageNumber(), paginationDTO.getRpp(), sort);
        long count = mongoOperations.count(textQuery, PaintingDTO.class);
        paintingDTOList = mongoTemplate.find(textQuery.with(pageable), PaintingDTO.class);

        return new PageImpl<>(paintingDTOList, pageable, count);
    }
}
