package com.demo.demo.service.productService;


import com.demo.demo.model.Product;
import org.springframework.stereotype.Service;
import com.demo.demo.repository.ProductRepository;

import java.util.List;

@Service
public class productService implements productServiceInterface {
    private final ProductRepository productRepository;

    public productService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> listProductByName(String name) {
        return productRepository.findByProductNameContaining(name);
    }
}
