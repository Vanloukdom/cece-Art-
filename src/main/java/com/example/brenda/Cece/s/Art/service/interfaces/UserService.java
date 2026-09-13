package com.example.brenda.Cece.s.Art.service.interfaces;

import com.example.brenda.Cece.s.Art.model.dto.UserDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;

public interface UserService {
    ResponseDto createAccount(UserDto userDto);
    ResponseDto getUserDetails(String userId);

    ResponseDto updateUserDetails(UserDto userDto,String id);

    ResponseDto getCustomers(int page);

    ResponseDto getAdmins(int page);
    ResponseDto deleteUser(String userId);
}
