package com.example.aiagent.service;

import com.example.aiagent.dto.ResponseResult;
import com.example.aiagent.entity.News;
import com.example.aiagent.repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public ResponseResult<?> getNewsById(String newsId) {
        Optional<News> newsOpt = newsRepository.findById(newsId);
        if (newsOpt.isEmpty()) {
            return ResponseResult.fail("新闻不存在");
        }
        return ResponseResult.success(newsOpt.get());
    }

    public ResponseResult<?> deleteNews(String newsId, String userId) {
        Optional<News> newsOpt = newsRepository.findById(newsId);
        if (newsOpt.isEmpty()) {
            return ResponseResult.fail("新闻不存在");
        }
        
        News news = newsOpt.get();
        if (!news.getAuthId().equals(userId)) {
            return ResponseResult.fail("无权删除该新闻");
        }
        
        newsRepository.deleteById(newsId);
        
        Map<String, String> result = new HashMap<>();
        result.put("newsId", newsId);
        result.put("title", news.getTitle());
        
        return ResponseResult.success(result, "删除成功");
    }

    public ResponseResult<?> getNewsList(int pageNum, int pageSize, String keyword, String authId) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "pubDate"));
        
        Page<News> newsPage;
        if (keyword != null && !keyword.isEmpty()) {
            newsPage = newsRepository.findByTitleContaining(keyword, pageable);
        } else if (authId != null && !authId.isEmpty()) {
            newsPage = newsRepository.findByAuthId(authId, pageable);
        } else {
            newsPage = newsRepository.findAll(pageable);
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("total", newsPage.getTotalElements());
        result.put("data", newsPage.getContent());
        
        return ResponseResult.success(result);
    }

    public ResponseResult<?> createNews(News news) {
        news.setNewsId(UUID.randomUUID().toString());
        news.setPubDate(LocalDateTime.now());
        news.setCreateTime(LocalDateTime.now());
        news.setUpdateTime(LocalDateTime.now());
        
        News savedNews = newsRepository.save(news);
        
        return ResponseResult.success(savedNews, "发布成功");
    }

    public ResponseResult<?> getLatestNews(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "pubDate"));
        List<News> latestNews = newsRepository.findLatestNews(pageable);
        
        return ResponseResult.success(latestNews);
    }
}