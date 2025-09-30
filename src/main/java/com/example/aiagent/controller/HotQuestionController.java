package com.example.aiagent.controller;

import com.example.aiagent.dto.ResponseResult;
import com.example.aiagent.entity.HotQuestion;
import com.example.aiagent.service.HotQuestionService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotQuestionController {

    private final HotQuestionService hotQuestionService;

    public HotQuestionController(HotQuestionService hotQuestionService) {
        this.hotQuestionService = hotQuestionService;
    }

    @GetMapping("/hotquestions")
    public ResponseResult<?> getHotQuestions(@RequestParam(defaultValue = "10") int limit) {
        return hotQuestionService.getHotQuestions(limit);
    }

    @PostMapping("/hotquestion/create")
    public ResponseResult<?> createHotQuestion(@RequestBody HotQuestion hotQuestion) {
        return hotQuestionService.createHotQuestion(hotQuestion);
    }

    @PutMapping("/hotquestion/{id}/view")
    public ResponseResult<?> incrementViewCount(@PathVariable String id) {
        return hotQuestionService.incrementViewCount(id);
    }

    @DeleteMapping("/hotquestion/{id}")
    public ResponseResult<?> deleteHotQuestion(@PathVariable String id) {
        return hotQuestionService.deleteHotQuestion(id);
    }
}