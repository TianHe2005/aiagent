package com.example.aiagent.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "hot_question")
public class HotQuestion {
    @Id
    private String id;
    private String title;
    
    @Column(name = "view_count")
    private Integer viewCount;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
}