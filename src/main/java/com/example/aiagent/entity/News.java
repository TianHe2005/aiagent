package com.example.aiagent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @Column(name = "news_id")
    private String newsId;
    
    @Column(name = "auth_id")
    private String authId;
    
    private String publisher;
    private String title;
    private String content;
    
    @Column(name = "cover_img_url")
    private String coverImgUrl;
    
    @Column(name = "pub_date")
    private LocalDateTime pubDate;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    
    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }
}