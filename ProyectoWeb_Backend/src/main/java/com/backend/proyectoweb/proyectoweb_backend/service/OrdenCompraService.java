package com.backend.proyectoweb.proyectoweb_backend.service;

import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.Producto;
import com.backend.proyectoweb.proyectoweb_backend.model.OrdenCompra;
import com.backend.proyectoweb.proyectoweb_backend.model.CarritoCompra;
import com.backend.proyectoweb.proyectoweb_backend.model.UsuarioSys;
import com.backend.proyectoweb.proyectoweb_backend.repository.OrdenCompraRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.UsusarioRepository;
import com.backend.proyectoweb.proyectoweb_backend.util.PurchaseOrderNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrdenCompraService implements IOrdenCompraService {

    @Autowired
    private OrdenCompraRepository repository;

    @Autowired
    private UsusarioRepository uRepository;

    @Override
    public void deletePurchaseOrder(Long id) {

        Optional<OrdenCompra> user = repository.findById(id);
        
        if(user.isPresent()){
            repository.delete(user.get());
        } else{
            throw new PurchaseOrderNotFoundException(id);
        }  
        
    }

    @Override
    public OrdenCompra updateOrder(OrdenCompra order, Long id) {
        
        return repository.findById(id).map(provider ->{

            provider.setFinalPrice(order.getFinalPrice());
            return repository.save(provider);

        }).orElseGet(()->{
            throw new PurchaseOrderNotFoundException(id);
        });
    }

    @Override
    public OrdenCompra getOrderById(Long id) {

        return repository.findById(id).orElseThrow(()-> new PurchaseOrderNotFoundException(id));

    }

    @Override
    public OrdenCompra createOrder(OrdenCompra order, Long id) {

        OrdenCompra order2 = new OrdenCompra();
        order2.setPurchaseDate(order.getPurchaseDate());
        order2.setCustomer(uRepository.getById(id));
        order2.setCart(order.getCart());
        order2.setFinalPrice(calcFinalPrice(order.getCart()));
        return repository.save(order);

    }

    @Override
    public Page<OrdenCompra> getOrders(Pageable pageable) {
        
        return repository.findAll(pageable);
    
    }
    

    public Double calcFinalPrice(CarritoCompra carritoCompra){
        
        Double finalprice = 0d;

        for(Producto p: carritoCompra.getProducts()){
            finalprice+=p.getPrice();
        }

        return finalprice;

    }

    @Override
    public Page<OrdenCompra> getOrdersPerUser(Long id, Pageable pageable) {

        UsuarioSys user = uRepository.getById(id);
        return repository.findByCustomer(user, pageable);
    }
}
