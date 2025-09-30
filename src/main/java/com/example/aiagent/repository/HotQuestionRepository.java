package com.example.aiagent.repository;

import com.example.aiagent.entity.HotQuestion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotQuestionRepository extends JpaRepository<HotQuestion, String> {
    List<HotQuestion> findAllByOrderByViewCountDesc(Pageable pageable);
}