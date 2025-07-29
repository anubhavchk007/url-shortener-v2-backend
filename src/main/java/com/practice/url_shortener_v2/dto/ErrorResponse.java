package com.practice.url_shortener_v2.dto;

public record ErrorResponse(
        String message,
        String path
) {
}
