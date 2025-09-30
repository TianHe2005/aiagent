package com.example.aiagent.repository;

import com.example.aiagent.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {
    Page<News> findByTitleContaining(String keyword, Pageable pageable);
    Page<News> findByAuthId(String authId, Pageable pageable);
    Page<News> findByPubDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
    
    @Query("SELECT n FROM News n ORDER BY n.pubDate DESC")
    List<News> findLatestNews(Pageable pageable);
}