package com.backend.proyectoweb.proyectoweb_backend.repository;

import java.util.Date;
import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.OrdenCompra;
import com.backend.proyectoweb.proyectoweb_backend.model.CarritoCompra;
import com.backend.proyectoweb.proyectoweb_backend.model.UsuarioSys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

    Optional<OrdenCompra> findById(Long id);

    Optional<OrdenCompra> findByCart(CarritoCompra cart);

    Page<OrdenCompra> findByPurchaseDate(Date purchaseDate, Pageable pageable);

    Page<OrdenCompra> findByCustomer(UsuarioSys customer, Pageable pageable);

}
