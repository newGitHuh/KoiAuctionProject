package com.demo.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productcategory")
    String categoryId;

    @Column(name = "categoryname")
    String categoryName;

    @OneToMany(mappedBy = "productCategory")
    List<Product> product;
}
