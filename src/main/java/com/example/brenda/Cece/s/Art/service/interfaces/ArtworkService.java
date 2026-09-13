package com.example.brenda.Cece.s.Art.service.interfaces;

import com.example.brenda.Cece.s.Art.model.dto.ArtworkDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;


public interface ArtworkService {

    ResponseDto getAllArtwork(int page);

    ResponseDto  getArtworkByCategory(String category,int page);

    ResponseDto getAvailableArts(boolean available,int page);

    ResponseDto updateArtwork(ArtworkDto artworkDto,String artworkId);

    ResponseDto deleteArtWork(String artworkId);
    ResponseDto addNewArt(ArtworkDto artworkDto);
}
