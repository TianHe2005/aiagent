package com.example.aiagent.controller;

import com.example.aiagent.dto.ResponseResult;
import com.example.aiagent.entity.News;
import com.example.aiagent.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news/{newsId}")
    public ResponseResult<?> getNewsById(@PathVariable String newsId) {
        return newsService.getNewsById(newsId);
    }

    @DeleteMapping("/news/{newsId}")
    public ResponseResult<?> deleteNews(@PathVariable String newsId, @RequestParam String userId) {
        return newsService.deleteNews(newsId, userId);
    }

    @GetMapping("/news/list")
    public ResponseResult<?> getNewsList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String authId) {
        return newsService.getNewsList(pageNum, pageSize, keyword, authId);
    }

    @PostMapping("/news/create")
    public ResponseResult<?> createNews(@RequestBody News news) {
        return newsService.createNews(news);
    }

    @GetMapping("/news/latest")
    public ResponseResult<?> getLatestNews(@RequestParam(defaultValue = "5") int limit) {
        return newsService.getLatestNews(limit);
    }
}