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

@Document(collection = "users")
public class Artwork {
    @Id
    private  String id;
    private  String title;
    private String description;
    private double price;
    private String category;
    private double deliveryFee;
    private String dimensions;
    private String imageUrl;
    private boolean availability;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
