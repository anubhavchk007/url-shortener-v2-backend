package com.practice.url_shortener_v2.event;

public record ShortUrlAccessedEvent(
        String shortCode
) {
}
