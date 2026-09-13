package com.example.brenda.Cece.s.Art.controller.interfaces;

import com.example.brenda.Cece.s.Art.model.dto.OrderDto;
import com.example.brenda.Cece.s.Art.model.enums.Status;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/orders")
public interface OrderController {

    @PostMapping
    ResponseDto addOrder(@RequestBody OrderDto orderDto);

    @GetMapping("/{orderId}")
    ResponseDto getOrderDetails(@PathVariable String orderId);

    @GetMapping("/{userId}/{page}")
    ResponseDto getAllUserOrder(@PathVariable String userId,@PathVariable int page);

    @GetMapping("{page}")
    ResponseDto getAllOrders(@PathVariable int page);

    @PutMapping("/{orderId}/{newStatus}")
    ResponseDto updateOrderStatus(@PathVariable String orderId,@PathVariable Status newStatus);



}
