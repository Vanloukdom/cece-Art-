package com.example.brenda.Cece.s.Art.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String artworkId;
    private String userId;
    private int rating;
    private String comment;
    private LocalDateTime timestamp;


}
