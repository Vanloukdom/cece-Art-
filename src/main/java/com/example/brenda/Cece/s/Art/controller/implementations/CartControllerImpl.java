package com.example.brenda.Cece.s.Art.controller.implementations;

import com.example.brenda.Cece.s.Art.controller.interfaces.CartController;
import com.example.brenda.Cece.s.Art.model.dto.CartDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import com.example.brenda.Cece.s.Art.service.interfaces.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CartControllerImpl implements CartController {

    private final CartService cartService;
    @Override
    public ResponseDto getAllItems(String userId,int page) {
        return cartService.getAllItems(userId,page);
    }

    @Override
    public ResponseDto addToCart(CartDto cartDto) {
        return cartService.addItem(cartDto);
    }

    @Override
    public ResponseDto updateQuantity(CartDto cartDto) {
        return cartService.updateQuantity(cartDto);
    }

    @Override
    public ResponseDto deleteItem(CartDto cartDto) {
        return cartService.deleteItem(cartDto);
    }
}
