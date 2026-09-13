package com.example.brenda.Cece.s.Art.model.dto;

import com.example.brenda.Cece.s.Art.model.entity.OrderedItems;
import com.example.brenda.Cece.s.Art.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private String userId;
    private List<OrderedItems> orderedItemsList;
    private String deliveryAddress;
    private boolean deliver;
    private String paymentMethod;
    private double amountPayed;

}
