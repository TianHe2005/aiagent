package com.example.aiagent.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "carousel")
public class Carousel {
    @Id
    private String id;
    
    @Column(name = "info_id")
    private String infoId;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    private String title;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    private Integer sort;
}