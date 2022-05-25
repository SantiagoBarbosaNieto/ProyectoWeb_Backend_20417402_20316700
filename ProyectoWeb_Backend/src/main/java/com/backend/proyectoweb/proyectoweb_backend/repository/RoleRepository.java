package com.backend.proyectoweb.proyectoweb_backend.repository;

import com.backend.proyectoweb.proyectoweb_backend.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
