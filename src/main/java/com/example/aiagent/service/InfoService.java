package com.example.aiagent.service;

import com.example.aiagent.dto.ResponseResult;
import com.example.aiagent.entity.Info;
import com.example.aiagent.repository.InfoRepository;
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
public class InfoService {

    private final InfoRepository infoRepository;

    public InfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    public ResponseResult<?> getInfoById(String infoId) {
        Optional<Info> infoOpt = infoRepository.findById(infoId);
        if (infoOpt.isEmpty()) {
            return ResponseResult.fail("通知不存在");
        }
        return ResponseResult.success(infoOpt.get());
    }

    public ResponseResult<?> deleteInfo(String infoId, String userId) {
        Optional<Info> infoOpt = infoRepository.findById(infoId);
        if (infoOpt.isEmpty()) {
            return ResponseResult.fail("通知不存在");
        }
        
        Info info = infoOpt.get();
        if (!info.getAuthId().equals(userId)) {
            return ResponseResult.fail("无权删除该通知");
        }
        
        infoRepository.deleteById(infoId);
        
        Map<String, String> result = new HashMap<>();
        result.put("infoId", infoId);
        result.put("title", info.getTitle());
        
        return ResponseResult.success(result, "删除成功");
    }

    public ResponseResult<?> getInfoList(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "pubDate"));
        Page<Info> infoPage = infoRepository.findAll(pageable);
        
        Map<String, Object> result = new HashMap<>();
        result.put("total", infoPage.getTotalElements());
        result.put("data", infoPage.getContent());
        
        return ResponseResult.success(result);
    }

    public ResponseResult<?> createInfo(Info info) {
        info.setInfoId(UUID.randomUUID().toString());
        info.setPubDate(LocalDateTime.now());
        info.setCreateTime(LocalDateTime.now());
        info.setUpdateTime(LocalDateTime.now());
        
        Info savedInfo = infoRepository.save(info);
        
        return ResponseResult.success(savedInfo, "发布成功");
    }

    public ResponseResult<?> getLatestInfo(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "pubDate"));
        List<Info> latestInfo = infoRepository.findLatestInfo(pageable);
        
        return ResponseResult.success(latestInfo);
    }
}