package com.example.brenda.Cece.s.Art.repository;

import com.example.brenda.Cece.s.Art.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    Page<Order> findAllByUserIdOrderByOrderDateDesc(String userId, Pageable pageable);
    Page<Order> findAllByOrderByOrderDateDesc(Pageable pageable);
    List<Order> findAllByUserId(String userId);
}
