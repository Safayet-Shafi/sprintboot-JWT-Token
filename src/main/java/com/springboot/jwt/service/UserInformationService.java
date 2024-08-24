package com.springboot.jwt.service;

import com.springboot.jwt.dto.ResponseDTO;
import com.springboot.jwt.dto.UserInformationDTO;

import java.security.NoSuchAlgorithmException;

public interface UserInformationService {

    ResponseDTO postUser(UserInformationDTO userInformationDTO) throws NoSuchAlgorithmException;

    String getUserPasswordByusername(String username);
}
