package com.backend.proyectoweb.proyectoweb_backend.service;

import com.backend.proyectoweb.proyectoweb_backend.model.CarritoCompra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICarritoCompraService {

    void deleteShoppingCart(Long id);

    CarritoCompra updateShoppingCart(CarritoCompra cart, Long id);

    CarritoCompra getShoppingCartById(Long id);

    CarritoCompra createShoppingCart(CarritoCompra carritoCompra, Long id);
    
    Page<CarritoCompra> getCarts(Pageable pageable);

    Page<CarritoCompra> getCartsPerUser(Long id, Pageable pageable);
}
