package com.example.brenda.Cece.s.Art.service.interfaces;

import com.example.brenda.Cece.s.Art.model.dto.ReviewDto;
import com.example.brenda.Cece.s.Art.model.entity.Review;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;

public interface ReviewService {
    ResponseDto getReviews(String artworkId,int page);

    ResponseDto addReview(ReviewDto reviewDto);

    ResponseDto updateReview(Review review);

    ResponseDto deleteReview(String reviewId);
}
