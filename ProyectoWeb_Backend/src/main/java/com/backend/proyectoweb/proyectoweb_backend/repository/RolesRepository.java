package com.backend.proyectoweb.proyectoweb_backend.repository;

import com.backend.proyectoweb.proyectoweb_backend.model.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{
    
}
