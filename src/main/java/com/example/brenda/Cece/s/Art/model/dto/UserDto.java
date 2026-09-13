package com.example.brenda.Cece.s.Art.model.dto;

import com.example.brenda.Cece.s.Art.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;
    private String Address;
}
