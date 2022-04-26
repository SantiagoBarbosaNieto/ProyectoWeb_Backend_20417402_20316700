package com.backend.proyectoweb.proyectoweb_backend.service;

import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.CarritoCompra;
import com.backend.proyectoweb.proyectoweb_backend.model.UsuarioSys;
import com.backend.proyectoweb.proyectoweb_backend.repository.CarritoCompraRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.UsusarioRepository;
import com.backend.proyectoweb.proyectoweb_backend.util.ShoppingCartNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarritoCompraService implements ICarritoCompraService {
    
    @Autowired
    private CarritoCompraRepository repository;
    
    @Autowired
    private UsusarioRepository uRepository;

    @Override
    public void deleteShoppingCart(Long id) {
        Optional<CarritoCompra> user = repository.findById(id);
        
        if(user.isPresent()){
            repository.delete(user.get());
        } else{
            throw new ShoppingCartNotFoundException(id);
        }  
    }

    @Override
    public CarritoCompra updateShoppingCart(CarritoCompra cart, Long id) {
        return repository.findById(id).map(provider ->{

            provider.setProducts(cart.getProducts());;
            return repository.save(provider);

        }).orElseGet(()->{
            throw new ShoppingCartNotFoundException(id);
        });
    }

    @Override
    public CarritoCompra getShoppingCartById(Long id) {
        return repository.findById(id).orElseThrow(()-> new ShoppingCartNotFoundException(id));
    }

    @Override
    public CarritoCompra createShoppingCart(CarritoCompra carritoCompra, Long id) {
        
        CarritoCompra cart = new CarritoCompra();
        cart.setUser(uRepository.getById(id));
        cart.setProducts(carritoCompra.getProducts());

        return repository.save(cart);
    }

    @Override
    public Page<CarritoCompra> getCarts(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<CarritoCompra> getCartsPerUser(Long id, Pageable pageable){

        UsuarioSys user = uRepository.getById(id);

        return repository.findByUser(user, pageable);
    }
    
}
