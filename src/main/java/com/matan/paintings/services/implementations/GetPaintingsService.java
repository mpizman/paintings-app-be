package com.matan.paintings.services.implementations;

import com.matan.paintings.models.implemenatations.PaintingDTO;
import com.matan.paintings.models.interfaces.IMiniPaintingDTO;
import com.matan.paintings.models.interfaces.IPaginationDTO;
import com.matan.paintings.models.interfaces.ISortDTO;
import com.matan.paintings.repository.PaintingRepository;
import com.matan.paintings.services.interfaces.IGetPaintingsService;
import com.matan.paintings.services.mappers.interfaces.IPaintingDTOToMiniPaintingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import java.util.List;
import java.util.stream.Collectors;

public class GetPaintingsService implements IGetPaintingsService {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    PaintingRepository paintingRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    IPaintingDTOToMiniPaintingDTO paintingDTOToMiniPaintingDTO;

    @Override
    public Page<IMiniPaintingDTO> execute(String searchQuery, ISortDTO sortDTO, IPaginationDTO paginationDTO) {
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

        return new PageImpl<>(mapPaintingListToMiniPaintingList(paintingDTOList), pageable, count);
    }

    List<IMiniPaintingDTO> mapPaintingListToMiniPaintingList(List<PaintingDTO> paintingDTOList) {
        return paintingDTOList.stream()
                .map(paintingDTO -> paintingDTOToMiniPaintingDTO.map(paintingDTO))
                .collect(Collectors.toList());
    }
}
