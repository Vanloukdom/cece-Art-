package com.example.brenda.Cece.s.Art.service.implementations;

import com.example.brenda.Cece.s.Art.mapper.Mapper;
import com.example.brenda.Cece.s.Art.model.dto.ReviewDto;
import com.example.brenda.Cece.s.Art.model.entity.Artwork;
import com.example.brenda.Cece.s.Art.model.entity.Review;
import com.example.brenda.Cece.s.Art.model.entity.User;
import com.example.brenda.Cece.s.Art.model.response.MetaDto;
import com.example.brenda.Cece.s.Art.model.response.PaginationDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import com.example.brenda.Cece.s.Art.repository.ReviewRepository;
import com.example.brenda.Cece.s.Art.service.interfaces.ReviewService;
import com.example.brenda.Cece.s.Art.utils.DateTimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private Mapper mapper;

    private final MongoTemplate mongoTemplate;

    private DateTimeUtil dateTimeUtil;

    public ResponseDto getReviews(String artworkId,int page){
        int size=5;
        if(reviewRepository.existsByArtworkId(artworkId)){
            Pageable paging = PageRequest.of(page, size);
            Page<Review> pageReviews = reviewRepository.findAllByArtworkIdOrderByTimestampDesc(artworkId,paging);
            MetaDto meta = new MetaDto(200,"Successful","Request Processed Successively");
            PaginationDto pagination = new PaginationDto(pageReviews.getTotalElements(), pageReviews.getNumberOfElements());
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, pageReviews.getContent(),error,pagination);
            return responseDto;
        }
        MetaDto meta = new MetaDto(404,"Not Found","No Reviews For This Art");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;
    }

    public ResponseDto addReview(ReviewDto reviewDto){
        if(reviewRepository.existsByUserIdAndArtworkIdAndComment(reviewDto.getUserId(), reviewDto.getArtworkId(),reviewDto.getComment())){
            MetaDto meta = new MetaDto(400,"Bad Request","Similar Review Exists");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        Review review=mapper.convertToEntity(reviewDto);
        reviewRepository.save(review);
        MetaDto meta = new MetaDto(200,"Successful","Request Processed Successively");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;
    }

    public ResponseDto updateReview(Review review){
        if(reviewRepository.existsById(review.getId())){
            Query query = new Query(Criteria.where("_id").is(review.getId()));
            Update update = new Update()
                    .set("rating", review.getRating())
                    .set("comment",review.getComment())
                    .set("timestamp",dateTimeUtil.getDateTime());

            mongoTemplate.findAndModify(query, update, Review.class);
            MetaDto meta = new MetaDto(200,"Successful","Request Processed Successively");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        MetaDto meta = new MetaDto(404,"Not Found","Review Not Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;
    }

    public ResponseDto deleteReview(String reviewId){
        if(reviewRepository.existsById(reviewId)){
            reviewRepository.deleteById(reviewId);
            MetaDto meta = new MetaDto(200,"Successful","Request Processed Successively");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        MetaDto meta = new MetaDto(404,"Not Found","Review Not Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;
    }
}
