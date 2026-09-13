package com.example.brenda.Cece.s.Art.controller.implementations;


import com.example.brenda.Cece.s.Art.controller.interfaces.ReviewController;
import com.example.brenda.Cece.s.Art.model.dto.ReviewDto;
import com.example.brenda.Cece.s.Art.model.entity.Review;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import com.example.brenda.Cece.s.Art.service.interfaces.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService reviewService;
    @Override
    public ResponseDto getReview(String artworkId,int page) {
        return reviewService.getReviews(artworkId,page);
    }

    @Override
    public ResponseDto addReview(ReviewDto reviewDto) {
        return  reviewService.addReview(reviewDto);
    }

    @Override
    public ResponseDto updateReview(Review review) {
        return reviewService.updateReview(review);
    }

    @Override
    public ResponseDto deleteReview(String reviewId) {
        return reviewService.deleteReview(reviewId);
    }
}
