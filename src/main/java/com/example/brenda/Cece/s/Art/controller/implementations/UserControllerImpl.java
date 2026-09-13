package com.example.brenda.Cece.s.Art.controller.implementations;

import com.example.brenda.Cece.s.Art.controller.interfaces.UserController;
import com.example.brenda.Cece.s.Art.model.dto.LoginDto;
import com.example.brenda.Cece.s.Art.model.dto.ReviewDto;
import com.example.brenda.Cece.s.Art.model.dto.UserDto;
import com.example.brenda.Cece.s.Art.model.entity.OrderedItems;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import com.example.brenda.Cece.s.Art.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public ResponseDto createNewUser(@RequestBody UserDto userDto) {

        return userService.createAccount(userDto);
    }

    public ResponseDto getAllCustomers(@PathVariable int page ) {

        return userService.getCustomers(page);
    }

    public ResponseDto getAllAdmins(@PathVariable int page) {

        return userService.getAdmins(page);
    }

    public ResponseDto loginUser(@RequestBody LoginDto loginDto) {
        return null;
    }

    public ResponseDto getUserInfo(@PathVariable String userId) {
        return userService.getUserDetails(userId);
    }

    public ResponseDto updateUserInfo(@RequestBody UserDto userDto, @PathVariable String userId) {
        return userService.updateUserDetails(userDto,userId);
    }

    public ResponseDto getUserOrders(@PathVariable String userId) {
        return null;
    }

    public ResponseDto deleteUser(@PathVariable String userId){
        return userService.deleteUser(userId);
    }

}
