package com.example.demo.test.service;

import com.example.demo.test.entity.Product;
import com.example.demo.test.helper.Helper;
import com.example.demo.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public String saveProduct(MultipartFile file) {
        try {
            List<Product> products = Helper.convertExcelToListOfProduct(file.getInputStream());
            List<Product> savedProducts = productRepository.saveAll(products);
            // Assuming the first product's ID is enough for the response
            return "Product created successfully with ID: " + savedProducts.get(0).getProductId();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save products: " + e.getMessage());
        }
    }
}
