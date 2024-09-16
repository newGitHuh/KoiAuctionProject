package com.demo.demo.repository;

import com.demo.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    ArrayList<Product> findByProductNameContaining(String name);
}
