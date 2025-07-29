package com.practice.url_shortener_v2.dto;

import com.practice.url_shortener_v2.entity.ShortUrlEntity;

import java.time.LocalDateTime;

public record ShortUrlStatsResponse(
        String id,
        String shortCode,
        String longUrl,
        Long accessCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public ShortUrlStatsResponse(ShortUrlEntity shortUrlEntity) {
        this(shortUrlEntity.getId(), shortUrlEntity.getShortCode(), shortUrlEntity.getLongUrl(),
                shortUrlEntity.getAccessCount(), shortUrlEntity.getCreatedAt(), shortUrlEntity.getUpdatedAt());
    }
}
