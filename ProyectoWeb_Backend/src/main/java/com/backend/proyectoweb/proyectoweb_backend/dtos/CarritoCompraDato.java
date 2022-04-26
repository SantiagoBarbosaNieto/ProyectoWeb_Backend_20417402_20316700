package com.backend.proyectoweb.proyectoweb_backend.dtos;

import java.util.List;

public class CarritoCompraDato {
    
    protected Long id;

    protected UsuarioDato user;

    protected List<ProductoDato> products;

    public CarritoCompraDato() {
    }

    public CarritoCompraDato(Long id, UsuarioDato user) {
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDato getUser() {
        return user;
    }

    public void setUser(UsuarioDato user) {
        this.user = user;
    }

    public List<ProductoDato> getProducts() {
        return products;
    }

    public void setProducts(List<ProductoDato> products) {
        this.products = products;
    }

    

}
