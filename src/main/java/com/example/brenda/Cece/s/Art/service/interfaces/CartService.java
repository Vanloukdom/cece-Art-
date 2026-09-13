package com.example.brenda.Cece.s.Art.service.interfaces;

import com.example.brenda.Cece.s.Art.model.dto.CartDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;

public interface CartService {
    ResponseDto getAllItems(String userId, int page);

    ResponseDto addItem(CartDto cartDto);

    ResponseDto updateQuantity(CartDto cartDto);

    ResponseDto deleteItem(CartDto cartDto);
}
