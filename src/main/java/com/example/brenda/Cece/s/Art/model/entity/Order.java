package com.example.brenda.Cece.s.Art.model.entity;

import com.example.brenda.Cece.s.Art.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private List<OrderedItems> orderedItemsList;
    private double totalAmount;
    private Status status;
    private String deliveryAddress;
    private double deliveryFee;
    private boolean deliver;
    private String paymentMethod;
    private double amountPayed;
    private LocalDateTime orderDate;

}
