package com.llm.walraimer.core.service;

import com.llm.walraimer.core.entity.Product;
import com.llm.walraimer.core.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

}
