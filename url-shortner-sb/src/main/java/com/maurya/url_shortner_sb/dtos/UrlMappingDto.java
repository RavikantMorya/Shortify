package com.maurya.url_shortner_sb.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlMappingDto {
    private Long id;
    private String original_url;
    private String short_url;
    private int clickCount=0;
    private LocalDateTime createdDate;
}
