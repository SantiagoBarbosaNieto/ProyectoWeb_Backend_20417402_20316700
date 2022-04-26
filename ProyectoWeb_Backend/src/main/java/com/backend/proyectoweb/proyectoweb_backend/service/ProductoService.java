package com.backend.proyectoweb.proyectoweb_backend.service;

import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.Producto;
import com.backend.proyectoweb.proyectoweb_backend.repository.ProductoRepository;
import com.backend.proyectoweb.proyectoweb_backend.util.ProductNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    public void deleteProduct(Long id) {
    
        Optional<Producto> producto = repository.findById(id);
        
        if(producto.isPresent()){
            repository.delete(producto.get());
        } else{
            throw new ProductNotFoundException(id);
        }  
        
    }

    @Override
    public Producto updateProduct(Producto producto, Long id) {
    
        return repository.findById(id).map(provider ->{

            provider.setName(producto.getName());
            provider.setDescription(producto.getDescription());
            provider.setImage(producto.getImage());
            provider.setPrice(producto.getPrice());

            return repository.save(provider);

        }).orElseGet(()->{
            throw new ProductNotFoundException(id);
        });
    
    }

    @Override
    public Producto getProductById(Long id) {

        return repository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
    
    }

    @Override
    public Producto createProduct(Producto producto) {
        
        return repository.save(producto);

    }

    @Override
    public Page<Producto> getProducts(Pageable pageable) {

        return repository.findAll(pageable);
    
    }
    
}
