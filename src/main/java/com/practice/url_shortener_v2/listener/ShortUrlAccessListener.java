package com.practice.url_shortener_v2.listener;

import com.practice.url_shortener_v2.entity.ShortUrlEntity;
import com.practice.url_shortener_v2.event.ShortUrlAccessedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class ShortUrlAccessListener {
    private final MongoTemplate mongoTemplate;

    public ShortUrlAccessListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Async
    @EventListener
    public void handleShortUrlAccess(ShortUrlAccessedEvent event) {
        Query query = new Query(Criteria.where("shortCode").is(event.shortCode()));
        Update update = new Update().inc("accessCount", 1);
        mongoTemplate.updateFirst(query, update, ShortUrlEntity.class);
    }
}
