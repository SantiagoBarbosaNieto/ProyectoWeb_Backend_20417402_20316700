package com.backend.proyectoweb.proyectoweb_backend.rest;

import java.util.ArrayList;
import java.util.List;

import com.backend.proyectoweb.proyectoweb_backend.anotations.isAdmin;
import com.backend.proyectoweb.proyectoweb_backend.anotations.isCustomer;
import com.backend.proyectoweb.proyectoweb_backend.anotations.isCustomerOrAdmin;
import com.backend.proyectoweb.proyectoweb_backend.dtos.CarritoCompraDato;
import com.backend.proyectoweb.proyectoweb_backend.model.CarritoCompra;
import com.backend.proyectoweb.proyectoweb_backend.service.ICarritoCompraService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ShoppingCarts")
public class CarritoCompraRest {

    @Autowired
    private ICarritoCompraService carritoCompraService;

    @Autowired
    private ModelMapper mapper;


    @isAdmin
    @GetMapping("{page}/{size}")
    public Page<CarritoCompraDato> getShoppingCarts(@PathVariable("page") int pagina, @PathVariable("size") int size){

        Pageable pageable = PageRequest.of(pagina, size, Sort.by("id"));

        Page<CarritoCompra> carts = carritoCompraService.getCarts(pageable);

        List<CarritoCompraDato> res = new ArrayList<>();

        for (CarritoCompra cart : carts.getContent()) {

            res.add(mapper.map(cart, CarritoCompraDato.class));

        }
        return new PageImpl<>(res, pageable,res.size());
    }

    @isCustomer
    @PostMapping(value = "create/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CarritoCompraDato createShoppingCart(@RequestBody CarritoCompraDato dto, @PathVariable("id") Long id){

        CarritoCompra cart = mapper.map(dto, CarritoCompra.class);

        return mapper.map(carritoCompraService.createShoppingCart(cart, id), CarritoCompraDato.class);

    }

    @isCustomerOrAdmin
    @PutMapping(value = "actulizar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CarritoCompraDato uCartDTO(@RequestBody CarritoCompraDato dto, @PathVariable Long id){

        CarritoCompra cart = mapper.map(dto, CarritoCompra.class);

        carritoCompraService.updateShoppingCart(cart, id);

        return dto;
    }

    @isCustomerOrAdmin
    @DeleteMapping("delete/{id}")
    public void deleteShoppingCart(@PathVariable Long id){
        carritoCompraService.deleteShoppingCart(id);
    }

    @isCustomerOrAdmin
    @GetMapping("carts/per/user/{id}/{page}/{size}")
    public Page<CarritoCompraDato> getCartsPerUser(@PathVariable("id") Long id, @PathVariable ("page") int page,
     @PathVariable("size") int size){

        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        Page<CarritoCompra> carts = carritoCompraService.getCartsPerUser(id, pageable);

        List<CarritoCompraDato> res = new ArrayList<>();

        for (CarritoCompra cart : carts.getContent()) {

            res.add(mapper.map(cart, CarritoCompraDato.class));

        }

        return new PageImpl<>(res, pageable,res.size());
    }

}