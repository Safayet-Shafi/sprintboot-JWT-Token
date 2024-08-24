package com.springboot.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationDTO {


    private Long id;
    private String username;
    private String email;
    private String password;
}
