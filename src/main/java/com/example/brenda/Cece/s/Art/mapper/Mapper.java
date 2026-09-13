package com.example.brenda.Cece.s.Art.mapper;

import com.example.brenda.Cece.s.Art.model.dto.ArtworkDto;
import com.example.brenda.Cece.s.Art.model.dto.OrderDto;
import com.example.brenda.Cece.s.Art.model.dto.ReviewDto;
import com.example.brenda.Cece.s.Art.model.dto.UserDto;
import com.example.brenda.Cece.s.Art.model.entity.*;
import com.example.brenda.Cece.s.Art.model.enums.Status;
import com.example.brenda.Cece.s.Art.repository.ArtworkRepository;
import com.example.brenda.Cece.s.Art.utils.DateTimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Mapper {
    private DateTimeUtil dateTimeUtil;

    private final ArtworkRepository artworkRepository;

    public User convertToEntity(UserDto userDto){
        User user=User.builder().Address(userDto.getAddress())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .phoneNumber(userDto.getPhoneNumber())
                .createdAt(dateTimeUtil.getDateTime())
                .updatedAt(dateTimeUtil.getDateTime())
                .build();
        return user;
    }
    public Artwork convertToEntity(ArtworkDto artworkDto){
        Artwork artwork=Artwork.builder()
                .title(artworkDto.getTitle())
                .description(artworkDto.getDescription())
                .category(artworkDto.getCategory())
                .price(artworkDto.getPrice())
                .availability(artworkDto.isAvailability())
                .imageUrl(artworkDto.getImageUrl())
                .dimensions(artworkDto.getDimensions())
                .createdAt(dateTimeUtil.getDateTime())
                .updatedAt(dateTimeUtil.getDateTime())
                .build();
        return artwork;
    }

    public Order convertToEntity(OrderDto orderDto){
        double deliveryFee=0;
        double totalAmount=0;
        List<OrderedItems> orderedItemsList=orderDto.getOrderedItemsList();
        for(int i=0;i<orderedItemsList.size();i++){
            Artwork artwork=artworkRepository.findArtworkById(orderedItemsList.get(i).getArtworkId());
            double amount=artwork.getPrice() * orderedItemsList.get(i).getQuantity();
            totalAmount+=amount;
        }
        if(orderDto.isDeliver()){
            for(int i=0;i<orderedItemsList.size();i++){
                deliveryFee=deliveryFee+orderedItemsList.get(i).getDeliveryFee();
            }

        }
        totalAmount=totalAmount+deliveryFee;

        Order order=Order.builder()
                .deliveryFee(deliveryFee)
                .amountPayed(0)
                .deliver(orderDto.isDeliver())
                .orderedItemsList(orderedItemsList)
                .deliveryAddress(orderDto.getDeliveryAddress())
                .orderDate(dateTimeUtil.getDateTime())
                .paymentMethod(orderDto.getPaymentMethod())
                .userId(orderDto.getUserId())
                .status(Status.PENDING)
                .totalAmount(totalAmount)
                .build();
        return order;
    }

    public Review convertToEntity(ReviewDto reviewDto){
        Review review=Review.builder()
                .rating(reviewDto.getRating())
                .userId(reviewDto.getUserId())
                .artworkId(reviewDto.getArtworkId())
                .comment(reviewDto.getComment())
                .timestamp(dateTimeUtil.getDateTime())
                .build();
        return review;
    }
}
