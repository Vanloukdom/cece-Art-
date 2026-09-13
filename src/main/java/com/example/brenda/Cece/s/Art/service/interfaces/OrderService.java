package com.example.brenda.Cece.s.Art.service.interfaces;

import com.example.brenda.Cece.s.Art.model.dto.OrderDto;
import com.example.brenda.Cece.s.Art.model.enums.Status;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;

public interface OrderService {
    ResponseDto addOrder(OrderDto orderDto);

    ResponseDto getOrderDetails(String orderId);

    ResponseDto getUserOrder(String userId,int page);

    ResponseDto getAllOrders(int page);

    ResponseDto updateOrderStatus(String orderId, Status newStatus);
}
