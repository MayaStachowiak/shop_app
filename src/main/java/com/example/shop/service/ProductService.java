package com.example.shop.service;

import com.example.shop.model.dao.Product;
import com.example.shop.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getPage(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional
    public Product updateById(Product product, Long id) {
        Product productDb = getById(id);
        productDb.setName(product.getName());
        productDb.setDescription(product.getDescription());
        productDb.setPrice(product.getPrice());
        productDb.setQuantity(product.getQuantity());
        return productDb;

    }

}
