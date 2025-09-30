package com.example.aiagent.repository;

import com.example.aiagent.entity.Carousel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarouselRepository extends JpaRepository<Carousel, String> {
    List<Carousel> findByIsActiveTrueOrderBySort();
    
    @Query("SELECT c FROM Carousel c ORDER BY c.sort ASC")
    List<Carousel> findActiveCarousels(Pageable pageable);
}