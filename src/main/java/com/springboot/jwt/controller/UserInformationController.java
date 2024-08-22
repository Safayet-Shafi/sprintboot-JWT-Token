package com.springboot.jwt.controller;

import com.springboot.jwt.dto.ResponseDTO;
import com.springboot.jwt.dto.UserInformationDTO;
import com.springboot.jwt.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/userinformation")
public class UserInformationController {

    @Autowired
    private UserInformationService userInformationService;

    @PostMapping()
    ResponseDTO postUser(@RequestBody UserInformationDTO userInformationDTO) throws NoSuchAlgorithmException {
        System.out.println("userInformationDTO = " + userInformationDTO);
        return userInformationService.postUser(userInformationDTO);
    }
}
