package com.example.brenda.Cece.s.Art.controller.interfaces;

import com.example.brenda.Cece.s.Art.model.dto.LoginDto;
import com.example.brenda.Cece.s.Art.model.dto.ReviewDto;
import com.example.brenda.Cece.s.Art.model.dto.UserDto;
import com.example.brenda.Cece.s.Art.model.entity.OrderedItems;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/users")
public interface UserController {

    @PostMapping("/register")
    ResponseDto createNewUser(@RequestBody UserDto userDto);

    @PostMapping("/login")
    ResponseDto loginUser(@RequestBody LoginDto loginDto);

    @GetMapping ("/profile/{userId}")
    ResponseDto getUserInfo(@PathVariable String userId);

    @PutMapping("/profile/{userId}")
    ResponseDto updateUserInfo(@RequestBody UserDto userDto,@PathVariable String userId);

    @GetMapping("/orders/{userId}")
    ResponseDto getUserOrders(@PathVariable String userId);

    @GetMapping("/customers/{page}")
    ResponseDto getAllCustomers(@PathVariable int page ) ;

    @GetMapping("/admins/{page}")
    ResponseDto getAllAdmins(@PathVariable int page) ;

    @DeleteMapping("/{userId}")
    ResponseDto deleteUser(@PathVariable String userId) ;


}
