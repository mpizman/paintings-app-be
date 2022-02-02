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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class GetPaintingsService implements IGetPaintingsService {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    PaintingRepository paintingRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Inject
    IPaintingDTOToMiniPaintingDTO paintingDTOToMiniPaintingDTO;

    @Override
    public Page<IMiniPaintingDTO> execute(String searchQuery,
                                          String uploaderUsername,
                                          String artist,
                                          String name,
                                          ISortDTO sortDTO,
                                          IPaginationDTO paginationDTO) {
        Sort sort;
        List<PaintingDTO> paintingDTOList;
        Query query = addCriteriaToQuery(searchQuery, uploaderUsername, artist, name);

        if (sortDTO.getField().equals("score") && searchQuery.isEmpty()) {
            throw new IllegalArgumentException("Cant sort by score for empty query");
        }

        if (!sortDTO.getField().isEmpty()) {
            sort = Sort.by(sortDTO.getOrder(), sortDTO.getField());
        } else {
            if (searchQuery.isEmpty()) {
                sort = Sort.by(sortDTO.getOrder(),"date");
            } else {
                sort = Sort.by(sortDTO.getOrder(),"score");
            }
        }

        Pageable pageable = PageRequest.of(paginationDTO.getPageNumber(), paginationDTO.getRpp(), sort);
        long count = mongoOperations.count(query, PaintingDTO.class);
        paintingDTOList = mongoTemplate.find(query.with(pageable), PaintingDTO.class);

        return new PageImpl<>(mapPaintingListToMiniPaintingList(paintingDTOList), pageable, count);
    }

    Query addCriteriaToQuery(String searchQuery, String uploaderUsername, String artist, String name) {
        Query query = new Query();
        if (searchQuery.length() > 0) {
            TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchQuery);
            query = TextQuery.queryText(textCriteria);
        }
        if (uploaderUsername.length() > 0) {
            query.addCriteria(Criteria.where("uploaderUsername").regex("(?i)^" + uploaderUsername +"$"));
        }
        if (artist.length() > 0) {
            query.addCriteria(Criteria.where("artist").regex("(?i)" + artist));
        }
        if (name.length() > 0) {
            query.addCriteria(Criteria.where("name").regex("(?i)^" + name +"$"));
        }
        return query;
    }

    List<IMiniPaintingDTO> mapPaintingListToMiniPaintingList(List<PaintingDTO> paintingDTOList) {
        return paintingDTOList.stream()
                .map(paintingDTO -> paintingDTOToMiniPaintingDTO.map(paintingDTO))
                .collect(Collectors.toList());
    }
}
