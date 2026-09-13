package com.example.brenda.Cece.s.Art.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtworkDto {

    private  String title;
    private String description;
    private double price;
    private String Category;
    private String deliveryFee;
    private String dimensions;
    private String imageUrl;
    private boolean availability;
}
