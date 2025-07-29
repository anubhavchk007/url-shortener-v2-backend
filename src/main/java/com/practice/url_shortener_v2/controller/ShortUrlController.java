package com.practice.url_shortener_v2.controller;

import com.practice.url_shortener_v2.dto.ShortUrlRequest;
import com.practice.url_shortener_v2.dto.ShortUrlResponse;
import com.practice.url_shortener_v2.dto.ShortUrlStatsResponse;
import com.practice.url_shortener_v2.service.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShortUrlController {
    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("shorten")
    public ShortUrlResponse shortenUrl(@RequestBody @Valid ShortUrlRequest shortUrlRequest) {
        return shortUrlService.shortenUrl(shortUrlRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("shorten/{shortCode}")
    public ShortUrlResponse retrieveUrl(@PathVariable String shortCode) {
        return shortUrlService.retrieveUrl(shortCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("shorten/{shortCode}")
    public ShortUrlResponse updateShortUrl(@PathVariable String shortCode, @RequestBody @Valid ShortUrlRequest shortUrlRequest) {
        return shortUrlService.updateShortUrl(shortCode, shortUrlRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("shorten/{shortCode}")
    public void deleteShortUrl(@PathVariable String shortCode) {
        shortUrlService.deleteShortUrl(shortCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("shorten/{shortCode}/stats")
    public ShortUrlStatsResponse retrieveUrlStats(@PathVariable String shortCode) {
        return shortUrlService.retrieveUrlStats(shortCode);
    }

}
