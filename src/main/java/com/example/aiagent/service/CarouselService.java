package com.example.aiagent.service;

import com.example.aiagent.dto.ResponseResult;
import com.example.aiagent.entity.Carousel;
import com.example.aiagent.repository.CarouselRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarouselService {

    private final CarouselRepository carouselRepository;

    public CarouselService(CarouselRepository carouselRepository) {
        this.carouselRepository = carouselRepository;
    }

    public ResponseResult<?> getActiveCarousels() {
        List<Carousel> carousels = carouselRepository.findByIsActiveTrueOrderBySort();
        return ResponseResult.success(carousels);
    }

    public ResponseResult<?> getAllCarousels() {
        List<Carousel> carousels = carouselRepository.findAll();
        return ResponseResult.success(carousels);
    }

    public ResponseResult<?> createCarousel(Carousel carousel) {
        Carousel savedCarousel = carouselRepository.save(carousel);
        return ResponseResult.success(savedCarousel, "轮播图创建成功");
    }

    public ResponseResult<?> updateCarousel(String id, Carousel carousel) {
        Optional<Carousel> existingCarousel = carouselRepository.findById(id);
        if (existingCarousel.isEmpty()) {
            return ResponseResult.fail("轮播图不存在");
        }
        
        carousel.setId(id);
        Carousel updatedCarousel = carouselRepository.save(carousel);
        return ResponseResult.success(updatedCarousel, "轮播图更新成功");
    }

    public ResponseResult<?> deleteCarousel(String id) {
        Optional<Carousel> existingCarousel = carouselRepository.findById(id);
        if (existingCarousel.isEmpty()) {
            return ResponseResult.fail("轮播图不存在");
        }
        
        carouselRepository.deleteById(id);
        return ResponseResult.success(null, "轮播图删除成功");
    }
}