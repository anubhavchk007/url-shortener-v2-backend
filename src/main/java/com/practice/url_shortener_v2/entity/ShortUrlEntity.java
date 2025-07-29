package com.practice.url_shortener_v2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "short-urls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlEntity {
    @Id
    @NonNull
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String shortCode;
    @NonNull
    private String longUrl;
    @NonNull
    private Long accessCount = 0L;
    @CreatedDate
    @NonNull
    private LocalDateTime createdAt;
    @NonNull
    private LocalDateTime updatedAt;

    public ShortUrlEntity(@NonNull String shortCode, @NonNull String longUrl) {
        this.shortCode = shortCode;
        this.longUrl = longUrl;
    }
}
