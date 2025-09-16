package com.maurya.url_shortner_sb.controller;
import com.maurya.url_shortner_sb.dtos.ClickEventDto;
import com.maurya.url_shortner_sb.dtos.UrlMappingDto;
import com.maurya.url_shortner_sb.models.User;
import com.maurya.url_shortner_sb.service.UrlMappingService;
import com.maurya.url_shortner_sb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/urls")
public class UrlMappingController {

    private UserService userService;
    private UrlMappingService urlMappingService;

    //{originalUrl:'https://www.google.com'}
    @GetMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createShortUrl(@RequestBody Map<String,String> request, Principal principal){
        String originalUrl = request.get("originalUrl");
        String username =principal.getName();
        User user = userService.findByUsername(username);
        UrlMappingDto urlMappingDto = urlMappingService.createShortUrl(originalUrl,user);
        return ResponseEntity.ok(urlMappingDto);
    }

    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserUrls(Principal principal){
        String username =principal.getName();
        User user = userService.findByUsername(username);
         List<UrlMappingDto> urlMappingDtoList = urlMappingService.getUserUrls(user);
         return ResponseEntity.ok(urlMappingDtoList);
    }




    @GetMapping("/analytics/{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<List<ClickEventDto>> getUrlAnalytics(@PathVariable String shortUrl,
                                                        @RequestParam("startDate") String start,
                                                        @RequestParam("endDate") String end){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime startD = LocalDateTime.parse(start,formatter);
        LocalDateTime endD = LocalDateTime.parse(end,formatter);
        List<ClickEventDto> clickEventDtos = urlMappingService.getClickEventsByDate(shortUrl,startD,endD);
        return ResponseEntity.ok(clickEventDtos);

    }

    @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Map<LocalDate,Long>> getTotalClicksByDate(Principal principal,
                                                             @RequestParam("startDate") String start,
                                                             @RequestParam("endDate") String end){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate startD = LocalDate.parse(start,formatter);
        LocalDate endD = LocalDate.parse(end,formatter);
        User user = userService.findByUsername(principal.getName());
        Map<LocalDate,Long> totalCliks = urlMappingService.getTotalClicksByUserAndDate(user,startD,endD);
        return ResponseEntity.ok(totalCliks);
    }


}
