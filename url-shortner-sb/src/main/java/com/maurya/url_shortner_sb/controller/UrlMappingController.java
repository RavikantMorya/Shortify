package com.maurya.url_shortner_sb.controller;
import com.maurya.url_shortner_sb.dtos.UrlMappingDto;
import com.maurya.url_shortner_sb.models.User;
import com.maurya.url_shortner_sb.service.UrlMappingService;
import com.maurya.url_shortner_sb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
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
    public ResponseEntity<?> getUserUrls(Principal principal){
        String username =principal.getName();
        User user = userService.findByUsername(username);
         List<UrlMappingDto> urlMappingDtoList = urlMappingService.getUserUrls(user);
         return ResponseEntity.ok(urlMappingDtoList);
    }
}
