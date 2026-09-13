package com.example.brenda.Cece.s.Art.service.implementations;

import com.example.brenda.Cece.s.Art.mapper.Mapper;
import com.example.brenda.Cece.s.Art.model.dto.OrderDto;

import com.example.brenda.Cece.s.Art.model.entity.Order;
import com.example.brenda.Cece.s.Art.model.entity.User;
import com.example.brenda.Cece.s.Art.model.enums.Status;
import com.example.brenda.Cece.s.Art.model.response.MetaDto;
import com.example.brenda.Cece.s.Art.model.response.PaginationDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import com.example.brenda.Cece.s.Art.repository.OrderRepository;
import com.example.brenda.Cece.s.Art.service.interfaces.OrderService;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private Mapper mapper;

    private final MongoTemplate mongoTemplate;

    @Override
    public ResponseDto addOrder(OrderDto orderDto) {
        Order order=mapper.convertToEntity(orderDto);
        orderRepository.save(order);
        MetaDto meta = new MetaDto(200,"Successful","New Order Added.Your Art Works are been prepared");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;
    }

    @Override
    public ResponseDto getOrderDetails(String orderId) {
        MetaDto meta = new MetaDto(200,"Successful","New Order Added.Your Art Works are been prepared");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, orderRepository.findById(orderId),error,pagination);
        return responseDto;
    }

    @Override
    public ResponseDto getUserOrder(String userId,int page) {
        int size=5;
        if(page<0 ){
            MetaDto meta = new MetaDto(400,"Bad Request","size and page should be greater than 0");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        List<Order> orders=orderRepository.findAllByUserId(userId);
        if(!orders.isEmpty()){
            Pageable paging = PageRequest.of(page, size);
            Page<Order> pageOrders = orderRepository.findAllByUserIdOrderByOrderDateDesc(userId,paging);
            MetaDto meta = new MetaDto(200,"Successful","Request processed successfully");
            PaginationDto pagination = new PaginationDto(pageOrders.getTotalElements(),pageOrders.getNumberOfElements() );
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta,pageOrders.getContent(),error,pagination);
            return responseDto;
        }

        MetaDto meta = new MetaDto(404,"Not Found","Order Is Empty");
        PaginationDto pagination = new PaginationDto(0,0);
        ResponseDto responseDto=new ResponseDto(meta,null,null,pagination);
        return responseDto;
    }

    @Override
    public ResponseDto getAllOrders(int page) {
        int size=5;
        if(page<0 ){
            MetaDto meta = new MetaDto(400,"Bad Request","size and page should be greater than 0");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        List<Order> orders=orderRepository.findAll();
        if(!orders.isEmpty()){
            Pageable paging = PageRequest.of(page, size);
            Page<Order> pageOrders = orderRepository.findAllByOrderByOrderDateDesc(paging);
            MetaDto meta = new MetaDto(200,"Successful","Request processed successfully");
            PaginationDto pagination = new PaginationDto(pageOrders.getTotalElements(),pageOrders.getNumberOfElements() );
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta,pageOrders.getContent(),error,pagination);
            return responseDto;
        }

        MetaDto meta = new MetaDto(404,"Not Found","Order Is Empty");
        PaginationDto pagination = new PaginationDto(0,0);
        ResponseDto responseDto=new ResponseDto(meta,null,null,pagination);
        return responseDto;
    }

    @Override
    public ResponseDto updateOrderStatus(String orderId, Status newStatus) {
        Optional<Order> order=orderRepository.findById(orderId);
        if(!order.isEmpty()){
            Query query = new Query(Criteria.where("_id").is(orderId));
            Update update = new Update()
                    .set("status", newStatus);
            mongoTemplate.findAndModify(query, update, User.class);
            MetaDto meta = new MetaDto(200,"Successful","Order Status Changed ");
            PaginationDto pagination = new PaginationDto(0,0 );
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta,null,error,pagination);
            return responseDto;
        }

        MetaDto meta = new MetaDto(404,"Not Found","Order Is Empty");
        PaginationDto pagination = new PaginationDto(0,0);
        ResponseDto responseDto=new ResponseDto(meta,null,null,pagination);
        return responseDto;
    }
}
