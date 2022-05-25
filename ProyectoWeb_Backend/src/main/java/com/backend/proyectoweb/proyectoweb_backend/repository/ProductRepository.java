package com.backend.proyectoweb.proyectoweb_backend.repository;

import java.util.List;
import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    List<Product> findByName(String name);

    @Query(value = "SELECT p FROM Product p WHERE p.price = ?1")
    List<Product> consulta1(Double price);
    
    
    
}
