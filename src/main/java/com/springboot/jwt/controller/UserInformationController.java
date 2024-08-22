package com.springboot.jwt.controller;

import com.springboot.jwt.dto.ResponseDTO;
import com.springboot.jwt.dto.UserInformationDTO;
import com.springboot.jwt.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/userinformation")
public class UserInformationController {

    @Autowired
    private UserInformationService userInformationService;

    @PostMapping("/userReg")
    ResponseDTO postUser(@RequestBody UserInformationDTO userInformationDTO) throws NoSuchAlgorithmException {
        System.out.println("userInformationDTO = " + userInformationDTO);
        return userInformationService.postUser(userInformationDTO);
    }

    @GetMapping("/user-check/{userId}")
    public String getUserPasswordByUserId(@PathVariable String userId) {
        return userInformationService.getUserPasswordByUserId(userId);
    }
}
