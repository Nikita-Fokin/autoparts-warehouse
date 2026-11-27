package com.example.Autoparts_warehouse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "parts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Brand brand;

    @DecimalMin("0.00")
    private BigDecimal price;

    @NotBlank
    private String art;

    @NotNull
    private Integer count;

    private String location;

}
