package com.backend.proyectoweb.proyectoweb_backend.rest;

import java.util.List;
import java.util.ArrayList;

import com.backend.proyectoweb.proyectoweb_backend.anotations.isAdmin;
import com.backend.proyectoweb.proyectoweb_backend.anotations.isCustomerOrAdmin;
import com.backend.proyectoweb.proyectoweb_backend.dtos.ProductoDato;
import com.backend.proyectoweb.proyectoweb_backend.model.Producto;
import com.backend.proyectoweb.proyectoweb_backend.service.IProductoService;

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
@RequestMapping("productos")
@isAdmin
public class ProductoRest {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductoDato createProdct(@RequestBody ProductoDato dto){
        Producto producto = mapper.map(dto, Producto.class);

        return  mapper.map(productoService.createProduct(producto), ProductoDato.class);
    }

    @isCustomerOrAdmin
    @GetMapping("{page}/{size}")
    public Page<ProductoDato> getProducts(@PathVariable("page") int pagina, @PathVariable("size") int size){

        Pageable pageable = PageRequest.of(pagina, size, Sort.by("id"));

        Page<Producto> productos = productoService.getProducts(pageable);

        List<ProductoDato> res = new ArrayList<>(); 

        for (Producto producto: productos.getContent()){

            res.add(mapper.map(producto, ProductoDato.class));
            
        }
        return new PageImpl<>(res, pageable, res.size());
    }

    @PutMapping(value = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductoDato updateProduct(@RequestBody ProductoDato dto, @PathVariable Long id){

        Producto producto = mapper.map(dto, Producto.class);

        productoService.updateProduct(producto, id);

        return dto;
    }

    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable Long id){

        productoService.deleteProduct(id);
    }

}
