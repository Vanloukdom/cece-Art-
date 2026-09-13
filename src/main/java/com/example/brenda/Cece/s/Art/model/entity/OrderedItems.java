package com.example.brenda.Cece.s.Art.model.entity;

import com.example.brenda.Cece.s.Art.utils.Fee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderedItems {
    private Fee fee;

    private String artworkId;
    private int quantity;
    private double deliveryFee=fee.getDeliveryFee(artworkId,quantity);
}
