package com.practice.url_shortener_v2.dto;

import jakarta.validation.constraints.NotBlank;

public record ShortUrlRequest(
    @NotBlank
    String url
) {
}
