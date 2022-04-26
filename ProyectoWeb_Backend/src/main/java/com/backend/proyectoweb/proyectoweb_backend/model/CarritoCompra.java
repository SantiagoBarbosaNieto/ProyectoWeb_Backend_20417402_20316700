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
public class CarritoCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @ManyToOne
    protected UsuarioSys user;

    // @OneToMany(fetch = FetchType.EAGER)
    // protected List<Producto> productos;

    @ManyToMany(fetch = FetchType.LAZY)
    protected List<Producto> productos;

    public CarritoCompra() {
    }

    public CarritoCompra(UsuarioSys user, List<Producto> productos) {
        this.user = user;
        this.productos = productos;
    }

    public UsuarioSys getUser() {
        return user;
    }

    public void setUser(UsuarioSys user) {
        this.user = user;
    }

    public List<Producto> getProducts() {
        return productos;
    }

    public void setProducts(List<Producto> productos) {
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

}
