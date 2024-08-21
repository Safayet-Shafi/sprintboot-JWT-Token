package com.springboot.jwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "products",uniqueConstraints = {@UniqueConstraint(columnNames={"productId"})}
)
public class Products {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @Column(name="productId")
    private String productId;

    @Column(name="productName")
    private String productName;

    @Column(name="productPrise")
    private String productPrise;


}
