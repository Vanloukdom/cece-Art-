package com.example.brenda.Cece.s.Art.utils;

import com.example.brenda.Cece.s.Art.model.entity.Artwork;
import com.example.brenda.Cece.s.Art.repository.ArtworkRepository;
import org.springframework.stereotype.Component;

@Component
public class Fee {

    private ArtworkRepository artworkRepository;

    public double getDeliveryFee(String artworkId,int quantity){
        Artwork artwork=artworkRepository.findArtworkById(artworkId);
        double index=Math.floor(quantity/5);
        double extraFee=index * artwork.getDeliveryFee();
        double deliveryFee=artwork.getDeliveryFee()+ extraFee;
        return deliveryFee;
    }
}
