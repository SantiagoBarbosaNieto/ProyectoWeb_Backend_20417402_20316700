package com.backend.proyectoweb.proyectoweb_backend.repository;

import java.util.Date;
import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.Product;
import com.backend.proyectoweb.proyectoweb_backend.model.PurchaseOrder;
import com.backend.proyectoweb.proyectoweb_backend.model.UserSys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    Optional<PurchaseOrder> findById(Long id);

    Optional<PurchaseOrder> findByProduct(Product product);

    Page<PurchaseOrder> findByPurchaseDate(Date purchaseDate, Pageable pageable);

    Page<PurchaseOrder> findByCustomer(UserSys customer, Pageable pageable);

}
