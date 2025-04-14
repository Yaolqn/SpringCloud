package com.example.product.controller;

import com.example.common.model.Result;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Result<com.example.product.entity.Product> getUser(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/{id}/deduct")
    public Result<com.example.product.entity.Product> deductStock(@PathVariable Long id, @RequestParam Integer amount) {
        return productService.deductStock(id, amount);
    }
}
