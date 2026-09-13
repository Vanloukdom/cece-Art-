package com.example.brenda.Cece.s.Art.controller.interfaces;

import com.example.brenda.Cece.s.Art.model.dto.ReviewDto;
import com.example.brenda.Cece.s.Art.model.entity.Review;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/reviews")
public interface ReviewController {


    @GetMapping("/{artworkId}/{page}")
    ResponseDto getReview(@PathVariable String artworkId,@PathVariable int page);

    @PostMapping
    ResponseDto addReview(@RequestBody ReviewDto reviewDto);

    @PutMapping
    ResponseDto updateReview(@RequestBody Review review);

    @DeleteMapping("/{reviewId}")
    ResponseDto deleteReview(@PathVariable String reviewId);

}