package com.backend.proyectoweb.proyectoweb_backend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ShoppingCart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @ManyToOne
    protected UserSys user;

    // @OneToMany(fetch = FetchType.EAGER)
    // protected List<Producto> productos;

    @ManyToMany(fetch = FetchType.LAZY)
    protected List<Product> products;

    public ShoppingCart() {
    }

    public ShoppingCart(UserSys user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public UserSys getUser() {
        return user;
    }

    public void setUser(UserSys user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

}
