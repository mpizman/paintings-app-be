package com.matan.paintings.configuration;

import com.matan.paintings.models.implemenatations.PaintingDTO;
import com.matan.paintings.models.implemenatations.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Collation;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
@DependsOn("mongoTemplate")
public class MongoDBConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndex() {
        mongoTemplate.indexOps(PaintingDTO.class)
                .ensureIndex(new Index().on("name", Sort.Direction.ASC)
                        .unique().collation(Collation.of(Locale.ENGLISH).strength(Collation.ComparisonLevel.secondary())));
        mongoTemplate.indexOps(UserDTO.class)
                .ensureIndex(new Index().on("username", Sort.Direction.ASC)
                        .unique().collation(Collation.of(Locale.ENGLISH).strength(Collation.ComparisonLevel.secondary())));
        mongoTemplate.indexOps(UserDTO.class)
                .ensureIndex(new Index().on("email", Sort.Direction.ASC)
                        .unique().collation(Collation.of(Locale.ENGLISH).strength(Collation.ComparisonLevel.secondary())));
    }
}
