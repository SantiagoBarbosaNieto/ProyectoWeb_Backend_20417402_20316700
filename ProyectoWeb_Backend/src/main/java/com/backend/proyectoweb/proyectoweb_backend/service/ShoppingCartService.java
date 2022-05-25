package com.backend.proyectoweb.proyectoweb_backend.service;

import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.ShoppingCart;
import com.backend.proyectoweb.proyectoweb_backend.model.UserSys;
import com.backend.proyectoweb.proyectoweb_backend.repository.ShoppingCartRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.UsusarioRepository;
import com.backend.proyectoweb.proyectoweb_backend.util.ShoppingCartNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService implements IShoppingCartService {
    
    @Autowired
    private ShoppingCartRepository repository;
    
    @Autowired
    private UsusarioRepository uRepository;

    @Override
    public void deleteShoppingCart(Long id) {
        Optional<ShoppingCart> user = repository.findById(id);
        
        if(user.isPresent()){
            repository.delete(user.get());
        } else{
            throw new ShoppingCartNotFoundException(id);
        }  
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart cart, Long id) {
        return repository.findById(id).map(provider ->{

            provider.setProducts(cart.getProducts());;
            return repository.save(provider);

        }).orElseGet(()->{
            throw new ShoppingCartNotFoundException(id);
        });
    }

    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        return repository.findById(id).orElseThrow(()-> new ShoppingCartNotFoundException(id));
    }

    @Override
    public ShoppingCart createShoppingCart(ShoppingCart carritoCompra, Long id) {
        
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(uRepository.getById(id));
        cart.setProducts(carritoCompra.getProducts());

        return repository.save(cart);
    }

    @Override
    public Page<ShoppingCart> getCarts(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<ShoppingCart> getCartsPerUser(Long id, Pageable pageable){

        UserSys user = uRepository.getById(id);

        return repository.findByUser(user, pageable);
    }
    
}
