package com.demo.demo.controller.productController;

import com.demo.demo.service.productService.productService;
import com.demo.demo.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final productService productService;

    public ProductController(com.demo.demo.service.productService.productService productService) {
        this.productService = productService;
    }

    @GetMapping("/listProduct")
    public List<Product> getProducts() {
        return productService.listProduct();
    }

    @GetMapping("/listProduct/{name}")
    public List<Product> listProductByName(@PathVariable String name) {
        return productService.listProductByName(name);
    }
}
