package com.example.brenda.Cece.s.Art.service.implementations;

import com.example.brenda.Cece.s.Art.mapper.Mapper;
import com.example.brenda.Cece.s.Art.model.dto.ArtworkDto;
import com.example.brenda.Cece.s.Art.model.entity.Artwork;
import com.example.brenda.Cece.s.Art.model.entity.User;
import com.example.brenda.Cece.s.Art.model.response.MetaDto;
import com.example.brenda.Cece.s.Art.model.response.PaginationDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import com.example.brenda.Cece.s.Art.repository.ArtworkRepository;
import com.example.brenda.Cece.s.Art.service.interfaces.ArtworkService;
import com.example.brenda.Cece.s.Art.utils.DateTimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtworkServiceImpl implements ArtworkService {

    private final ArtworkRepository artworkRepository;

    private Mapper mapper;

    private final MongoTemplate mongoTemplate;

    private DateTimeUtil dateTimeUtil;

    public ResponseDto getAllArtwork(int page) {
        int size=15;
        if(page<0 ){
            MetaDto meta = new MetaDto(400,"Bad Request","size and page should be greater than 0");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        List<Artwork> artworks=artworkRepository.findAll();
        if(!artworks.isEmpty()){
            Pageable paging = PageRequest.of(page, size);
            Page<Artwork> pageLoans = artworkRepository.findAll(paging);
            MetaDto meta = new MetaDto(200,"Successful","Request processed successfully");
            PaginationDto pagination = new PaginationDto(pageLoans.getTotalElements(), pageLoans.getSize());
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, pageLoans.getContent(),error,pagination);
            return responseDto;
        }

        MetaDto meta = new MetaDto(404,"Not Found","No Art Work Found");
        PaginationDto pagination = new PaginationDto(0,0);
        ResponseDto responseDto=new ResponseDto(meta,null,null,pagination);
        return responseDto;
    }

    public ResponseDto getArtworkByCategory(String category,int page) {
        int size=15;
        if(page<0 ){
            MetaDto meta = new MetaDto(400,"Bad Request","size and page should be greater than 0");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        List<Artwork> artworks=artworkRepository.findAllByCategory(category);
        if(!artworks.isEmpty()){
            Pageable paging = PageRequest.of(page, size);
            Page<Artwork> pageLoans = artworkRepository.findAllByCategoryOrderByCreatedAtDesc(category,paging);
            MetaDto meta = new MetaDto(200,"Successful","Request processed successfully");
            PaginationDto pagination = new PaginationDto(pageLoans.getTotalElements(), pageLoans.getSize());
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, pageLoans.getContent(),error,pagination);
            return responseDto;
        }

        MetaDto meta = new MetaDto(404,"Not Found","No Art Work Found");
        PaginationDto pagination = new PaginationDto(0,0);
        ResponseDto responseDto=new ResponseDto(meta,null,null,pagination);
        return responseDto;
    }

    public ResponseDto getAvailableArts(boolean available,int page) {
        int size=15;
        if(page<0 ){
            MetaDto meta = new MetaDto(400,"Bad Request","size and page should be greater than 0");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        List<Artwork> artworks=artworkRepository.findAllByAvailabilityIsTrue();
        if(!artworks.isEmpty()){
            Pageable paging = PageRequest.of(page, size);
            Page<Artwork> pageLoans = artworkRepository.findAllByAvailabilityIsTrueOrderByCreatedAtDesc(paging);
            MetaDto meta = new MetaDto(200,"Successful","Request processed successfully");
            PaginationDto pagination = new PaginationDto(pageLoans.getTotalElements(), pageLoans.getSize());
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, pageLoans.getContent(),error,pagination);
            return responseDto;
        }

        MetaDto meta = new MetaDto(404,"Not Found","No Art Work Found");
        PaginationDto pagination = new PaginationDto(0,0);
        ResponseDto responseDto=new ResponseDto(meta,null,null,pagination);
        return responseDto;
    }

    public ResponseDto updateArtwork(ArtworkDto artworkDto, String artworkId) {
        if(artworkRepository.existsById(artworkId)){
            Query query = new Query(Criteria.where("_id").is(artworkId));
            Update update = new Update()
                    .set("title", artworkDto.getTitle())
                    .set("category",artworkDto.getCategory())
                    .set("description",artworkDto.getDescription())
                    .set("price",artworkDto.getPrice())
                    .set("dimensions",artworkDto.getDimensions())
                    .set("imageUrl",artworkDto.getImageUrl())
                    .set("updated_At",dateTimeUtil.getDateTime());
            mongoTemplate.findAndModify(query, update, User.class);
            MetaDto meta = new MetaDto(200,"Successfull","Request Processed Successively");
            PaginationDto pagination = new PaginationDto(1, 1);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        MetaDto meta = new MetaDto(404,"Not Found","Art Work Not Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;

    }

    public ResponseDto deleteArtWork(String artworkId) {
        if(artworkRepository.existsById(artworkId)){
            MetaDto meta = new MetaDto(200,"Successfull","Request Processed Successively");
            PaginationDto pagination = new PaginationDto(1, 1);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        MetaDto meta = new MetaDto(404,"Not Found","Art Work Not Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;
    }

    public ResponseDto addNewArt(ArtworkDto artworkDto){
        if(artworkRepository.existsByTitleOrImageUrl(artworkDto.getTitle(),artworkDto.getImageUrl())){
            MetaDto meta = new MetaDto(400,"Bad Request","Art Work Already Exist By Title and Image");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        artworkRepository.save(mapper.convertToEntity(artworkDto));
        MetaDto meta = new MetaDto(200,"Successfull","Request Processed Successively");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;
    }
}
