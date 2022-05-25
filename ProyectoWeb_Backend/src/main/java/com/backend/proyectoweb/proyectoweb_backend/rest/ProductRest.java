package com.backend.proyectoweb.proyectoweb_backend.rest;

import java.util.List;
import java.util.ArrayList;

import com.backend.proyectoweb.proyectoweb_backend.anotations.isAdmin;
import com.backend.proyectoweb.proyectoweb_backend.anotations.isCustomerOrAdmin;
import com.backend.proyectoweb.proyectoweb_backend.dtos.ProductDTO;
import com.backend.proyectoweb.proyectoweb_backend.model.Product;
import com.backend.proyectoweb.proyectoweb_backend.service.IProductService;

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
public class ProductRest {

    @Autowired
    private IProductService productoService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO createProdct(@RequestBody ProductDTO dto){
        Product product = mapper.map(dto, Product.class);

        return  mapper.map(productoService.createProduct(product), ProductDTO.class);
    }

    @isCustomerOrAdmin
    @GetMapping("{page}/{size}")
    public Page<ProductDTO> getProducts(@PathVariable("page") int pagina, @PathVariable("size") int size){

        Pageable pageable = PageRequest.of(pagina, size, Sort.by("id"));

        Page<Product> productos = productoService.getProducts(pageable);

        List<ProductDTO> res = new ArrayList<>();

        for (Product product : productos.getContent()){

            res.add(mapper.map(product, ProductDTO.class));
            
        }
        return new PageImpl<>(res, pageable, res.size());
    }

    @PutMapping(value = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO updateProduct(@RequestBody ProductDTO dto, @PathVariable Long id){

        Product product = mapper.map(dto, Product.class);

        productoService.updateProduct(product, id);

        return dto;
    }

    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable Long id){

        productoService.deleteProduct(id);
    }

}
