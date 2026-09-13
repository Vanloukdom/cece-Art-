package com.example.brenda.Cece.s.Art.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private String artworkId;
    private String userId;
    private int rating;
    private String comment;
}
