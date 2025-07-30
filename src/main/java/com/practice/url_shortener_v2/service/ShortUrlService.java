package com.practice.url_shortener_v2.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.practice.url_shortener_v2.dto.ShortUrlRequest;
import com.practice.url_shortener_v2.dto.ShortUrlResponse;
import com.practice.url_shortener_v2.dto.ShortUrlStatsResponse;
import com.practice.url_shortener_v2.entity.ShortUrlEntity;
import com.practice.url_shortener_v2.event.ShortUrlAccessedEvent;
import com.practice.url_shortener_v2.exception.NotFoundException;
import com.practice.url_shortener_v2.repository.ShortUrlRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;


@Service
public class ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ShortUrlService(ShortUrlRepository shortUrlRepository, ApplicationEventPublisher eventPublisher) {
        this.shortUrlRepository = shortUrlRepository;
        this.eventPublisher = eventPublisher;
    }

    public ShortUrlResponse shortenUrl(ShortUrlRequest shortUrlRequest) {
        String shortCode;
        do {
            shortCode = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NanoIdUtils.DEFAULT_ALPHABET, 7);
        } while (shortUrlRepository.findByShortCode(shortCode).isPresent());
        ShortUrlEntity shortUrl = new ShortUrlEntity(shortCode, shortUrlRequest.url());
        shortUrlRepository.save(shortUrl);

        return new ShortUrlResponse(shortUrl);
    }

    public ShortUrlResponse retrieveUrl(String shortCode) {
        ShortUrlEntity shortUrl = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new NotFoundException("Short URL was not found."));

        eventPublisher.publishEvent(new ShortUrlAccessedEvent(shortCode));

        return new ShortUrlResponse(shortUrl);
    }

    public ShortUrlResponse updateShortUrl(String shortCode, ShortUrlRequest shortUrlRequest) {
        ShortUrlEntity shortUrl = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new NotFoundException("Short URL was not found."));
        shortUrl.setLongUrl(shortUrlRequest.url());
        shortUrl.setUpdatedAt(LocalDateTime.now());
        shortUrlRepository.save(shortUrl);

        return new ShortUrlResponse(shortUrl);
    }

    public void deleteShortUrl(String shortCode) {
        ShortUrlEntity shortUrl = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new NotFoundException("Short URL was not found."));
        shortUrlRepository.delete(shortUrl);
    }

    public ShortUrlStatsResponse retrieveUrlStats(String shortCode) {
        return shortUrlRepository.findByShortCode(shortCode)
                .map(ShortUrlStatsResponse::new)
                .orElseThrow(() -> new NotFoundException("Short URL not found."));
    }

    public RedirectView redirectUrl(String shortCode) {
        ShortUrlEntity shortUrl = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new NotFoundException("Short URL was not found."));
        String longUrl = shortUrl.getLongUrl();
        if (!longUrl.startsWith("http")) {
            longUrl = "https://" + longUrl;
        }
        return new RedirectView(longUrl);
    }
}
