package com.example.aiagent.controller;

import com.example.aiagent.dto.ResponseResult;
import com.example.aiagent.entity.Info;
import com.example.aiagent.service.InfoService;
import org.springframework.web.bind.annotation.*;

@RestController
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/info/{infoId}")
    public ResponseResult<?> getInfoById(@PathVariable String infoId) {
        return infoService.getInfoById(infoId);
    }

    @DeleteMapping("/info/{infoId}")
    public ResponseResult<?> deleteInfo(@PathVariable String infoId, @RequestParam String userId) {
        return infoService.deleteInfo(infoId, userId);
    }

    @GetMapping("/info/list")
    public ResponseResult<?> getInfoList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        return infoService.getInfoList(pageNum, pageSize);
    }

    @PostMapping("/info/create")
    public ResponseResult<?> createInfo(@RequestBody Info info) {
        return infoService.createInfo(info);
    }

    @GetMapping("/info/latest")
    public ResponseResult<?> getLatestInfo(@RequestParam(defaultValue = "5") int limit) {
        return infoService.getLatestInfo(limit);
    }
}