package com.backend.proyectoweb.proyectoweb_backend.repository;

import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.ShoppingCart;
import com.backend.proyectoweb.proyectoweb_backend.model.UserSys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{

    Optional<ShoppingCart> findById(Long id);

    Page<ShoppingCart> findByUser(UserSys user, Pageable pageable);

}
