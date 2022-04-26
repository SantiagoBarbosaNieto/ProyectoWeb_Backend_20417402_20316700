package com.backend.proyectoweb.proyectoweb_backend.repository;

import java.util.List;
import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findById(Long id);

    List<Producto> findByName(String name);

    @Query(value = "SELECT p FROM Producto p WHERE p.price = ?1")
    List<Producto> consulta1(Double price);
    
    
    
}
