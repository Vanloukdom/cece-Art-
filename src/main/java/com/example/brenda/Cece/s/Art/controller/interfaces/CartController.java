package com.example.brenda.Cece.s.Art.controller.interfaces;

import com.example.brenda.Cece.s.Art.model.dto.CartDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/cart")
public interface CartController {

    @GetMapping("/{userId}/{page}")
    ResponseDto getAllItems(@PathVariable String userId,@PathVariable int page);

    @PostMapping
    ResponseDto addToCart(@RequestBody CartDto cartDto);

    @PutMapping
    ResponseDto updateQuantity(@RequestBody CartDto cartDto);

    @DeleteMapping
    ResponseDto deleteItem(@RequestBody CartDto cartDto);

}
