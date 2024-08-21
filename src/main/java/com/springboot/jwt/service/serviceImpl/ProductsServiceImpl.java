package com.springboot.jwt.service.serviceImpl;

import com.springboot.jwt.dto.ProductsDTO;
import com.springboot.jwt.model.Products;
import com.springboot.jwt.repository.ProducrRepo;
import com.springboot.jwt.service.ProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProducrRepo producrRepo;

    private final ModelMapper mapper;

    public ProductsServiceImpl(ProducrRepo producrRepo, ModelMapper mapper) {
        this.producrRepo = producrRepo;
        this.mapper = mapper;
    }

    @Override
    public List<ProductsDTO> getAllProducts() {
        List<Products> products=producrRepo.findAll();
        return products.stream()  // Convert the collection 'employees' into a Stream
                .map(employee -> maptoDTO(employee))  // Map each 'employee' object to its corresponding DTO using the 'mapToDTO' function
                .toList();  // Convert the resulting Stream into a List
    }

    private ProductsDTO maptoDTO(Products products) {
        ProductsDTO productsDTO = mapper.map(products, ProductsDTO.class);
        return productsDTO;
    }
}
