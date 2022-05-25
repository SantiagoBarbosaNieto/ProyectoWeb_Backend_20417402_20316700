package com.backend.proyectoweb.proyectoweb_backend.dtos;

import java.util.Date;

public class PurchaseOrderDTO {

    protected Long id;

    protected UserDTO customer;

    protected Double finalPrice;

    protected ProductDTO product;

    protected Date purchaseDate;

    public PurchaseOrderDTO() {
    }

    public PurchaseOrderDTO(UserDTO customer, Double finalPrice, ProductDTO product, Date purchaseDate) {
        this.customer = customer;
        this.finalPrice = finalPrice;
        this.product = product;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public UserDTO getCustomer() {
        return customer;
    }

    public void setCustomer(UserDTO customer) {
        this.customer = customer;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    

}
