package com.backend.proyectoweb.proyectoweb_backend.dtos;

import java.util.List;

public class ShoppingCartDTO {
    
    protected Long id;

    protected UserDTO user;

    protected List<ProductDTO> products;

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(Long id, UserDTO user) {
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    

}
