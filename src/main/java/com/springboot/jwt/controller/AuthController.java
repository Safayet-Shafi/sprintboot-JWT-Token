package com.springboot.jwt.controller;


import com.springboot.jwt.dto.AuthRequest;
import com.springboot.jwt.dto.ResponseDTO;
import com.springboot.jwt.service.JwtService;
import com.springboot.jwt.service.UserInformationService;
import com.springboot.jwt.service.serviceImpl.UserInformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInformationServiceImpl userInformationService;

    String generatedPass="";
    String dbPass="";

    @PostMapping("/token")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws NoSuchAlgorithmException {
        System.out.println("authRequest = " + authRequest);
        generatedPass=userInformationService.customHash((String) authRequest.getUsername(), (String) authRequest.getPassword());
        dbPass=userInformationService.getUserPasswordByusername((String) authRequest.getUsername());

        if(generatedPass.equals(dbPass)){
            return jwtService.generateToken(authRequest.getUsername());
        }
        else{

            return ("Error in UserId or Password");
        }
        

    }
}
