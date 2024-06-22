package com.example.shop.controller;

import com.example.shop.mapper.ProductMapper;
import com.example.shop.model.dao.Product;
import com.example.shop.model.dto.ProductDto;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productMapper.map(productService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        Product productToSave = productService.save(productMapper.map(productDto));
        return productMapper.map(productToSave);
    }

    @PutMapping("/{id}")
    public ProductDto updateProductById(@RequestBody ProductDto product, @PathVariable Long id) {
        return productMapper.map(productService.updateById(productMapper.map(product), id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/all")
    public Page<ProductDto> getProductPage(@RequestParam int page, @RequestParam int size) {
        Page<Product> products = productService.getPage(page, size);
        return products.map(productMapper::map);
    }

}
