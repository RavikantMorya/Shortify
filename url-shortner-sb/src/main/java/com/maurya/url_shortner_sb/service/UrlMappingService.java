package com.maurya.url_shortner_sb.service;

import com.maurya.url_shortner_sb.dtos.ClickEventDto;
import com.maurya.url_shortner_sb.dtos.UrlMappingDto;
import com.maurya.url_shortner_sb.models.ClickEvents;
import com.maurya.url_shortner_sb.models.UrlMapping;
import com.maurya.url_shortner_sb.models.User;
import com.maurya.url_shortner_sb.repository.ClickEventRepository;
import com.maurya.url_shortner_sb.repository.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UrlMappingService {

    private UrlMappingRepository urlMappingRepository;
    private ClickEventRepository clickEventRepository;

    public UrlMappingDto createShortUrl(String originalUrl, User user) {

        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginal_url(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());
        urlMappingRepository.save(urlMapping);
        return convertToDto(urlMapping);
    }

    private UrlMappingDto convertToDto(UrlMapping urlMapping) {
        UrlMappingDto urlMappingDto = new UrlMappingDto();
        urlMappingDto.setId(urlMapping.getId());
        urlMappingDto.setOriginal_url(urlMapping.getOriginal_url());
        urlMappingDto.setShort_url(urlMapping.getShortUrl());
        urlMappingDto.setClickCount(urlMapping.getClickCount());
        urlMappingDto.setCreatedDate(urlMapping.getCreatedDate());
        return urlMappingDto;
    }



    private String generateShortUrl() {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            shortUrl.append(characters.charAt(index));
        }

        return shortUrl.toString();
    }

    public List<UrlMappingDto> getUserUrls(User user) {
       return urlMappingRepository.findByUser(user).stream()
                .map(this::convertToDto).toList();
    }

    public List<ClickEventDto> getClickEventsByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping!= null){
            return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping,start,end).stream()
                    .collect(Collectors.groupingBy(click->click.getClickDate().toLocalDate(),
                            Collectors.counting()))
                    .entrySet().stream()
                    .map(entry->{
                        ClickEventDto clickEventDto = new ClickEventDto();
                        clickEventDto.setClickDate(entry.getKey());
                        clickEventDto.setCount(entry.getValue());
                        return clickEventDto;
                    })
                    .collect(Collectors.toList());
        }
        return null;
    }


    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate startD, LocalDate endD) {
      List<UrlMapping> urlMapping= urlMappingRepository.findByUser(user);
      List<ClickEvents> clickEventsList =clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMapping, startD.atStartOfDay(),endD.plusDays(1).atStartOfDay());
      return clickEventsList.stream().collect(
              Collectors.groupingBy(
                      clickEvents -> clickEvents.getClickDate().toLocalDate(), Collectors.counting()
              )
      );
    }
}


