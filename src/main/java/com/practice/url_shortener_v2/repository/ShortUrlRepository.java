package com.practice.url_shortener_v2.repository;

import com.practice.url_shortener_v2.entity.ShortUrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;

public interface ShortUrlRepository extends MongoRepository<ShortUrlEntity, String> {
    Optional<ShortUrlEntity> findByShortCode(String shortCode);
}
