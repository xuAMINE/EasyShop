package com.uup.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String name;
    @Lob
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}

