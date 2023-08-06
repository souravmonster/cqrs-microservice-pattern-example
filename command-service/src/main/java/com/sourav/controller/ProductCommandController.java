package com.sourav.controller;

import com.sourav.dto.ProductEvent;
import com.sourav.entity.Product;
import com.sourav.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    @Autowired
    private ProductCommandService service;


    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductEvent event) {
        Product created = service.createProduct(event);
        if (!Optional.ofNullable(created).isEmpty()) {
            return new ResponseEntity<String>("Product has been added", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("No product has been added", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable long id, @RequestBody ProductEvent event) {
        Product updated = service.updateProduct(id, event);
        if (!Optional.ofNullable(updated).isEmpty()) {
            return new ResponseEntity<String>("Product has been updated", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("No product has been updated", HttpStatus.BAD_REQUEST);
    }
}
