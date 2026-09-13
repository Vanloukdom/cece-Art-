package com.example.brenda.Cece.s.Art.controller.interfaces;

import com.example.brenda.Cece.s.Art.model.dto.ArtworkDto;
import com.example.brenda.Cece.s.Art.model.entity.Artwork;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/artwork")
public interface ArtworkController {
    @GetMapping("/{page}")
    ResponseDto getAllArtwork(@PathVariable int page);
    @PostMapping
    ResponseDto addNewArt(ArtworkDto artworkDto);
    @GetMapping ("/{category}/{page}")
    ResponseDto getArtworkByCategory(@PathVariable String category,@PathVariable int page);
    @GetMapping ("/{available}/{page}")
    ResponseDto getAvailableArts(@PathVariable boolean available,@PathVariable int page);
    @PutMapping("/{artworkId}")
    ResponseDto updateArtwork(@RequestBody ArtworkDto artworkDto,@PathVariable String artworkId);
    @DeleteMapping("/{artId}")
    ResponseDto deleteArtwork(@PathVariable String artId);
}
