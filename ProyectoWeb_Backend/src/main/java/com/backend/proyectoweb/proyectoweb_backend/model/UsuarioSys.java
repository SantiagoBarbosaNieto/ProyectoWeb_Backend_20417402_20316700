package com.backend.proyectoweb.proyectoweb_backend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;


@Entity
public class UsuarioSys {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column
    protected String firstName;

    @Column
    protected String lastName;

    @Column
    @Temporal(TemporalType.DATE)
    protected Date birthDate;
    
    @Column(unique = true)
    @Email
    protected String email;

    @Column
    protected String password;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Roles rol;

    @OneToMany(mappedBy = "customer")
    protected List<OrdenCompra> orders;

    @OneToMany(mappedBy = "user")
    protected List<CarritoCompra> carts;

    
    public UsuarioSys() {
    }

    public UsuarioSys(String firstName, String lastName, Date birthDate, @Email String email, String password,
            List<OrdenCompra> orders, List<CarritoCompra> carts, Roles rol) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.orders = orders;
        this.carts = carts;
        this.rol = rol;
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


    public List<OrdenCompra> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdenCompra> orders) {
        this.orders = orders;
    }

    public List<CarritoCompra> getCarts() {
        return carts;
    }

    public void setCarts(List<CarritoCompra> carts) {
        this.carts = carts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    
}
