package com.maurya.url_shortner_sb.repository;

import com.maurya.url_shortner_sb.models.ClickEvents;
import com.maurya.url_shortner_sb.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvents,Long> {

   List<ClickEvents> findByUrlMappingAndClickDateBetween(UrlMapping urlmapping, LocalDateTime startDate, LocalDateTime endDate);

   List<ClickEvents> findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMappingList, LocalDateTime startDate, LocalDateTime endDate);


}

