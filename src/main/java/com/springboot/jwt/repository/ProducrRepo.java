package com.springboot.jwt.repository;

import com.springboot.jwt.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducrRepo extends JpaRepository<Products,Long> {
}
