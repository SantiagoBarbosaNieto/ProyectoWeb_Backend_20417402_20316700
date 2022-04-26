package com.backend.proyectoweb.proyectoweb_backend.service;

import com.backend.proyectoweb.proyectoweb_backend.model.Producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductoService {

    void deleteProduct(Long id);

    Producto updateProduct(Producto producto, Long id);

    Producto getProductById(Long  id);

    Producto createProduct(Producto producto);
    
    Page<Producto> getProducts(Pageable pageable);
    
}
