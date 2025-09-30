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
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    
    private String username;
    private String password;
    private String role;
    
    @Column(name = "avatar_url")
    private String avatarUrl;
    
    private Integer sex;
    private String college;
    
    @Column(name = "class_name")
    private String className;
    
    @Column(name = "admission_year")
    private String admissionYear;
    
    @Column(name = "school_year_system")
    private String schoolYearSystem;
    
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