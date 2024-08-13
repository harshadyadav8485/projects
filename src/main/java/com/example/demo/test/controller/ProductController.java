package com.example.demo.test.controller;

import com.example.demo.test.entity.Employee;
import com.example.demo.test.entity.Product;
import com.example.demo.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestParam("file") MultipartFile file) {
        try {
            String saveProduct = productService.saveProduct(file);
            return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create employee", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
