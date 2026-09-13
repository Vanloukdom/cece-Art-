package com.example.brenda.Cece.s.Art.repository;

import com.example.brenda.Cece.s.Art.model.entity.Artwork;
import com.example.brenda.Cece.s.Art.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review,String> {
    Page<Review> findAllByArtworkIdOrderByTimestampDesc(String artworkId, Pageable pageable);
    boolean existsByArtworkId(String artworkId);
    boolean existsByUserIdAndArtworkIdAndComment(String userId,String comment,String artworkId);
}
