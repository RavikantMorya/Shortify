package com.maurya.url_shortner_sb.controller;

import com.maurya.url_shortner_sb.models.UrlMapping;
import com.maurya.url_shortner_sb.service.UrlMappingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class RedirectController {

    private UrlMappingService urlMappingService;

    @GetMapping("{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl){

        UrlMapping urlMapping = urlMappingService.getOriginalUrl(shortUrl);
        if (urlMapping!=null){

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location",urlMapping.getOriginal_url());
            return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).build();
        }
        return ResponseEntity.notFound().build();
    }
}
