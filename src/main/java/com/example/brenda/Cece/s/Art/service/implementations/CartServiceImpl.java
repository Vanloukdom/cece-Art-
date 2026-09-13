package com.example.brenda.Cece.s.Art.service.implementations;

import com.example.brenda.Cece.s.Art.model.dto.CartDto;
import com.example.brenda.Cece.s.Art.model.entity.Cart;
import com.example.brenda.Cece.s.Art.model.entity.OrderedItems;
import com.example.brenda.Cece.s.Art.model.entity.User;
import com.example.brenda.Cece.s.Art.model.response.MetaDto;
import com.example.brenda.Cece.s.Art.model.response.PaginationDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import com.example.brenda.Cece.s.Art.repository.CartRepository;
import com.example.brenda.Cece.s.Art.service.interfaces.CartService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    
    private final MongoTemplate mongoTemplate;

    public ResponseDto getAllItems(String userId, int page){
        int size=5;
        if(page<0 ){
            MetaDto meta = new MetaDto(400,"Bad Request","size and page should be greater than 0");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        Cart cart=cartRepository.findByUserId(userId);
        List<OrderedItems> cartItems=cart.getItems();
        if(!cartItems.isEmpty()){
            List<OrderedItems> actualPage=new ArrayList<>();
            int skip=page*size;
            for(int i=skip;i<skip+size;i++){
                if(skip+size>=cartItems.size()){
                    break;
                }
                actualPage.add(cartItems.get(i));
            }

            MetaDto meta = new MetaDto(200,"Successful","Request processed successfully");
            PaginationDto pagination = new PaginationDto(cartItems.size(), actualPage.size());
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, actualPage,error,pagination);
            return responseDto;
        }

        MetaDto meta = new MetaDto(404,"Not Found","Your Cart Is Empty");
        PaginationDto pagination = new PaginationDto(0,0);
        ResponseDto responseDto=new ResponseDto(meta,null,null,pagination);
        return responseDto;
    }

    public ResponseDto addItem(CartDto cartDto){
        Cart cart=cartRepository.findByUserId(cartDto.getUserId());
        List<OrderedItems> cartItems=cart.getItems();
       
        String itemId1=cartDto.getItems().getArtworkId();
        if(cartItems.size()>=20){
            MetaDto meta = new MetaDto(400,"Bad Request","Too many Items In your Cart");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        for(int i=0;i<cartItems.size();i++){
            String itemId2=cartItems.get(i).getArtworkId();
            if(itemId1.equals(itemId2)){
                MetaDto meta = new MetaDto(400,"Bad Request","Art Work Already Exist In Cart");
                PaginationDto pagination = new PaginationDto(0, 0);
                String error=null;
                ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
                return responseDto; 
            }
        }
        cartItems.add(cartDto.getItems());
        Query query = new Query(Criteria.where("userId").is(cartDto.getUserId()));
        Update update = new Update()
                .set("items", cartItems);
                
        mongoTemplate.findAndModify(query, update, Cart.class);
        MetaDto meta = new MetaDto(200,"Successfull","Request Processed Successively");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;
    }

    public ResponseDto updateQuantity(CartDto cartDto){
        Cart cart=cartRepository.findByUserId(cartDto.getUserId());
        List<OrderedItems> cartItems=cart.getItems();
        int newQuantity=cartDto.getItems().getQuantity();
        String itemId1=cartDto.getItems().getArtworkId();
        for(int i=0;i<cartItems.size();i++){
            String itemId2=cartItems.get(i).getArtworkId();
            if(itemId1.equals(itemId2)){
                cartItems.get(i).setQuantity(newQuantity);
                Query query = new Query(Criteria.where("userId").is(cartDto.getUserId()));
                Update update = new Update()
                        .set("items", cartItems);

                mongoTemplate.findAndModify(query, update, Cart.class);
                MetaDto meta = new MetaDto(200,"Successfull","Request Processed Successively");
                PaginationDto pagination = new PaginationDto(0, 0);
                String error=null;
                ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
                return responseDto;
            }
        }
       

       
        MetaDto meta = new MetaDto(404,"Not Found","Item Not Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;
    }

    public ResponseDto deleteItem(CartDto cartDto){

        Cart cart=cartRepository.findByUserId(cartDto.getUserId());
        List<OrderedItems> cartItems=cart.getItems();

        String itemId1=cartDto.getItems().getArtworkId();
       
        for(int i=0;i<cartItems.size();i++){
            String itemId2=cartItems.get(i).getArtworkId();
            if(itemId1.equals(itemId2)){
                cartItems.remove(i);
                Query query = new Query(Criteria.where("userId").is(cartDto.getUserId()));
                Update update = new Update()
                        .set("items", cartItems);

                mongoTemplate.findAndModify(query, update, Cart.class);
                MetaDto meta = new MetaDto(200,"Successfull","Request Processed Successively");
                PaginationDto pagination = new PaginationDto(0, 0);
                String error=null;
                ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
                return responseDto;
            }
        }
        MetaDto meta = new MetaDto(404,"Not Found","Item Not Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;
      
      
    }

}
