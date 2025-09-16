package com.maurya.url_shortner_sb.repository;

import com.maurya.url_shortner_sb.models.UrlMapping;
import com.maurya.url_shortner_sb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long>{

    UrlMapping findByShortUrl(String shortUrl);
    List<UrlMapping> findByUser(User user);
}
