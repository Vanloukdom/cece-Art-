package com.example.brenda.Cece.s.Art.controller.implementations;

import com.example.brenda.Cece.s.Art.controller.interfaces.ArtworkController;
import com.example.brenda.Cece.s.Art.model.dto.ArtworkDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import com.example.brenda.Cece.s.Art.service.interfaces.ArtworkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class ArtworkControllerImpl implements ArtworkController {

    private final ArtworkService artworkService;

    public ResponseDto getAllArtwork(@PathVariable int page) {
        return artworkService.getAllArtwork(page);
    }

    public ResponseDto getArtworkByCategory(@PathVariable String category,@PathVariable int page) {
        return artworkService.getArtworkByCategory(category,page);
    }

    public ResponseDto getAvailableArts(@PathVariable boolean available,@PathVariable int page) {
        return artworkService.getAvailableArts(available,page);
    }

    public ResponseDto updateArtwork(@RequestBody ArtworkDto artworkDto,@PathVariable String artworkId) {
        return artworkService.updateArtwork(artworkDto,artworkId);
    }

    public ResponseDto deleteArtwork(@PathVariable String artworkId) {
        return artworkService.deleteArtWork(artworkId);
    }

    public ResponseDto addNewArt(ArtworkDto artworkDto){
        return artworkService.addNewArt(artworkDto);
    }
}
