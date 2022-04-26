package com.backend.proyectoweb.proyectoweb_backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class OrdenCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne
    protected UsuarioSys customer;

    @Column
    protected Double finalPrice;

    @OneToOne
    protected CarritoCompra cart;

    @Column
    @Temporal(TemporalType.DATE)
    protected Date purchaseDate;


    public OrdenCompra() {
    }

    public OrdenCompra(UsuarioSys customer, Double finalPrice, CarritoCompra cart, Date purchaseDate) {
        this.customer = customer;
        this.finalPrice = finalPrice;
        this.cart = cart;
        this.purchaseDate = purchaseDate;
    }

    public UsuarioSys getCustomer() {
        return customer;
    }

    public void setCustomer(UsuarioSys customer) {
        this.customer = customer;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public CarritoCompra getCart() {
        return cart;
    }

    public void setCart(CarritoCompra cart) {
        this.cart = cart;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}