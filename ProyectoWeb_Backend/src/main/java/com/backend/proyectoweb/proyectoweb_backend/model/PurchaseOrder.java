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
public class PurchaseOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne
    protected UserSys customer;

    @Column
    protected Double finalPrice;

    @ManyToOne
    protected Product product;

    @Column
    @Temporal(TemporalType.DATE)
    protected Date purchaseDate;


    public PurchaseOrder() {
    }

    public PurchaseOrder(UserSys customer, Double finalPrice, Product product, Date purchaseDate) {
        this.customer = customer;
        this.finalPrice = finalPrice;
        this.product = product;
        this.purchaseDate = purchaseDate;
    }

    public UserSys getCustomer() {
        return customer;
    }

    public void setCustomer(UserSys customer) {
        this.customer = customer;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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