package com.demo.demo.service.productService;

import com.demo.demo.model.Product;

import java.util.List;

public interface productServiceInterface {
    public List<Product> listProduct();

    public List<Product> listProductByName(String name);
}
