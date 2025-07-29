package com.practice.url_shortener_v2.dto;

import com.practice.url_shortener_v2.entity.ShortUrlEntity;

import java.time.LocalDateTime;

public record ShortUrlResponse(
        String id,
        String shortCode,
        String longUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public ShortUrlResponse(ShortUrlEntity shortUrlEntity) {
        this(shortUrlEntity.getId(), shortUrlEntity.getShortCode(), shortUrlEntity.getLongUrl(),
                shortUrlEntity.getCreatedAt(), shortUrlEntity.getUpdatedAt());
    }
}
