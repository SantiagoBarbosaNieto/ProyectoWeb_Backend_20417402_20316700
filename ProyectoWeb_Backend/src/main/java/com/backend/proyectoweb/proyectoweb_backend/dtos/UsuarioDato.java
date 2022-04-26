package com.backend.proyectoweb.proyectoweb_backend.dtos;

import java.util.Date;

public class UsuarioDato {
    
    protected Long id;

    protected String firstName;

    protected String lastName;

    protected Date birthDate;

    protected String email;

    protected String password;

    protected RolesDato rol;

    // protected List<OrdenCompraDato> orders;

    // protected List<CarritoCompraDato> carts;


    public UsuarioDato() {
    }


    public UsuarioDato(String firstName, String lastName, Date birthDate, String email, String password, RolesDato rol) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public RolesDato getRol() {
        return rol;
    }


    public void setRol(RolesDato rol) {
        this.rol = rol;
    }


    // public List<OrdenCompraDato> getOrders() {
    //     return orders;
    // }


    // public void setOrders(List<OrdenCompraDato> orders) {
    //     this.orders = orders;
    // }


    // public List<CarritoCompraDato> getCarts() {
    //     return carts;
    // }


    // public void setCarts(List<CarritoCompraDato> carts) {
    //     this.carts = carts;
    // }


}
