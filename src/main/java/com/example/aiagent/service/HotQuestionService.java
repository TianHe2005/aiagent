package com.example.aiagent.service;

import com.example.aiagent.dto.ResponseResult;
import com.example.aiagent.entity.HotQuestion;
import com.example.aiagent.repository.HotQuestionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HotQuestionService {

    private final HotQuestionRepository hotQuestionRepository;

    public HotQuestionService(HotQuestionRepository hotQuestionRepository) {
        this.hotQuestionRepository = hotQuestionRepository;
    }

    public ResponseResult<?> getHotQuestions(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "viewCount"));
        List<HotQuestion> hotQuestions = hotQuestionRepository.findAll(pageable).getContent();
        return ResponseResult.success(hotQuestions);
    }

    public ResponseResult<?> createHotQuestion(HotQuestion hotQuestion) {
        hotQuestion.setCreateTime(LocalDateTime.now());
        hotQuestion.setViewCount(0);
        HotQuestion savedQuestion = hotQuestionRepository.save(hotQuestion);
        return ResponseResult.success(savedQuestion, "热门问题创建成功");
    }

    public ResponseResult<?> incrementViewCount(String id) {
        Optional<HotQuestion> questionOpt = hotQuestionRepository.findById(id);
        if (questionOpt.isEmpty()) {
            return ResponseResult.fail("问题不存在");
        }
        
        HotQuestion question = questionOpt.get();
        question.setViewCount(question.getViewCount() + 1);
        hotQuestionRepository.save(question);
        
        return ResponseResult.success(question);
    }

    public ResponseResult<?> deleteHotQuestion(String id) {
        Optional<HotQuestion> questionOpt = hotQuestionRepository.findById(id);
        if (questionOpt.isEmpty()) {
            return ResponseResult.fail("问题不存在");
        }
        
        hotQuestionRepository.deleteById(id);
        return ResponseResult.success(null, "问题删除成功");
    }
}