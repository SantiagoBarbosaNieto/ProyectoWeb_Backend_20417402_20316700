package com.backend.proyectoweb.proyectoweb_backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Roles {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column
    protected String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
    protected List<UsuarioSys> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UsuarioSys> getUsers() {
        return users;
    }

    public void setUsers(List<UsuarioSys> users) {
        this.users = users;
    }

}
