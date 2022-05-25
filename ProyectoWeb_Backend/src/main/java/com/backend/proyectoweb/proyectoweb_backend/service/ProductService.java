package com.backend.proyectoweb.proyectoweb_backend.service;

import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.Product;
import com.backend.proyectoweb.proyectoweb_backend.repository.ProductRepository;
import com.backend.proyectoweb.proyectoweb_backend.util.ProductNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public void deleteProduct(Long id) {
    
        Optional<Product> producto = repository.findById(id);
        
        if(producto.isPresent()){
            repository.delete(producto.get());
        } else{
            throw new ProductNotFoundException(id);
        }  
        
    }

    @Override
    public Product updateProduct(Product product, Long id) {
    
        return repository.findById(id).map(provider ->{

            provider.setName(product.getName());
            provider.setDescription(product.getDescription());
            provider.setImage(product.getImage());
            provider.setPrice(product.getPrice());

            return repository.save(provider);

        }).orElseGet(()->{
            throw new ProductNotFoundException(id);
        });
    
    }

    @Override
    public Product getProductById(Long id) {

        return repository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
    
    }

    @Override
    public Product createProduct(Product product) {
        
        return repository.save(product);

    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {

        return repository.findAll(pageable);
    
    }
    
}
