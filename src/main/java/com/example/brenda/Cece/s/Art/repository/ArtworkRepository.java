package com.example.brenda.Cece.s.Art.repository;

import com.example.brenda.Cece.s.Art.model.entity.Artwork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArtworkRepository extends MongoRepository<Artwork,String> {
    Page<Artwork> findAllByCategoryOrderByCreatedAtDesc(String category, Pageable pageable);
    List<Artwork> findAllByCategory(String category);
    Page <Artwork> findAllByAvailabilityIsTrueOrderByCreatedAtDesc(Pageable pageable);
    List<Artwork> findAllByAvailabilityIsTrue();
    boolean existsByTitleOrImageUrl(String title,String imageUrl);
    Artwork findArtworkById(String id);

}
