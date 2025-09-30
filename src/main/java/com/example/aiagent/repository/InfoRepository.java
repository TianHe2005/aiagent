package com.example.aiagent.repository;

import com.example.aiagent.entity.Info;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<Info, String> {
    @Query("SELECT i FROM Info i ORDER BY i.pubDate DESC")
    List<Info> findLatestInfo(Pageable pageable);
}