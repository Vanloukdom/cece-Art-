package com.example.brenda.Cece.s.Art.model.dto;

import com.example.brenda.Cece.s.Art.model.entity.OrderedItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private String userId;
    private OrderedItems items;
}
