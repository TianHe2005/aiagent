package com.example.aiagent.controller;

import com.example.aiagent.dto.ResponseResult;
import com.example.aiagent.entity.Carousel;
import com.example.aiagent.service.CarouselService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarouselController {

    private final CarouselService carouselService;

    public CarouselController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @GetMapping("/carousel/active")
    public ResponseResult<?> getActiveCarousels() {
        return carouselService.getActiveCarousels();
    }

    @GetMapping("/carousel/all")
    public ResponseResult<?> getAllCarousels() {
        return carouselService.getAllCarousels();
    }

    @PostMapping("/carousel/create")
    public ResponseResult<?> createCarousel(@RequestBody Carousel carousel) {
        return carouselService.createCarousel(carousel);
    }

    @PutMapping("/carousel/{id}")
    public ResponseResult<?> updateCarousel(@PathVariable String id, @RequestBody Carousel carousel) {
        return carouselService.updateCarousel(id, carousel);
    }

    @DeleteMapping("/carousel/{id}")
    public ResponseResult<?> deleteCarousel(@PathVariable String id) {
        return carouselService.deleteCarousel(id);
    }
}