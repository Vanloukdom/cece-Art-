package com.example.brenda.Cece.s.Art.controller.implementations;

import com.example.brenda.Cece.s.Art.controller.interfaces.OrderController;
import com.example.brenda.Cece.s.Art.model.dto.OrderDto;
import com.example.brenda.Cece.s.Art.model.enums.Status;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import com.example.brenda.Cece.s.Art.service.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    public ResponseDto addOrder(OrderDto orderDto) {

        return orderService.addOrder(orderDto);
    }

    @Override
    public ResponseDto getOrderDetails(String orderId) {
        return orderService.getOrderDetails(orderId);
    }

    @Override
    public ResponseDto getAllUserOrder(String userId,int page) {
        return orderService.getUserOrder(userId,page);
    }

    @Override
    public ResponseDto getAllOrders(int page) {
        return orderService.getAllOrders(page);
    }

    @Override
    public ResponseDto updateOrderStatus(String orderId, Status newStatus) {
        return orderService.updateOrderStatus(orderId,newStatus);
    }
}
