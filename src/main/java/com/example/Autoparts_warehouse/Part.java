package com.example.Autoparts_warehouse;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Category category;
    private String art;
    private String brand;
    private Integer count;
}
