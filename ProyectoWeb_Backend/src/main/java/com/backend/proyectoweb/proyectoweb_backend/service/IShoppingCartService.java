package com.backend.proyectoweb.proyectoweb_backend.service;

import com.backend.proyectoweb.proyectoweb_backend.model.ShoppingCart;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IShoppingCartService {

    void deleteShoppingCart(Long id);

    ShoppingCart updateShoppingCart(ShoppingCart cart, Long id);

    ShoppingCart getShoppingCartById(Long id);

    ShoppingCart createShoppingCart(ShoppingCart carritoCompra, Long id);
    
    Page<ShoppingCart> getCarts(Pageable pageable);

    Page<ShoppingCart> getCartsPerUser(Long id, Pageable pageable);
}
