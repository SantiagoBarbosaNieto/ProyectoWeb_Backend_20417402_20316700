package com.backend.proyectoweb.proyectoweb_backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column
    protected String name;

    @Column
    protected String desarrollador;

    @Column
    protected String description;

    @Column
    protected Double price;

    @Column
    protected String image;

    public Product() {
    }

    public Product(String name, String description, String desarrollador, Double price, String image) {
        this.name = name;
        this.description = description;
        this.desarrollador = desarrollador;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesarrollador() {return desarrollador; }

    public void setDesarrollador(String desarrollador) { this.desarrollador = desarrollador;}

    
}