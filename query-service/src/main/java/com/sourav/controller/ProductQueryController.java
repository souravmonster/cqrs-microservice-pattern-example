package com.sourav.controller;


import com.sourav.entity.Product;
import com.sourav.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    private ProductQueryService service;

    @GetMapping
    public List<Product> fetchAllProducts() {
        return service.getProducts();
    }
}
