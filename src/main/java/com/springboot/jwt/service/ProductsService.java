package com.springboot.jwt.service;

import com.springboot.jwt.dto.ProductsDTO;

import java.util.List;

public interface ProductsService {

    List<ProductsDTO> getAllProducts ();
}
