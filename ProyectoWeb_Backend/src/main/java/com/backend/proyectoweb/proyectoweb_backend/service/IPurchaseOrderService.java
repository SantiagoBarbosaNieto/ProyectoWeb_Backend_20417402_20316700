package com.backend.proyectoweb.proyectoweb_backend.service;

import com.backend.proyectoweb.proyectoweb_backend.model.PurchaseOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPurchaseOrderService {
    
    void deletePurchaseOrder(Long id);

    PurchaseOrder updateOrder(PurchaseOrder order, Long id);

    PurchaseOrder getOrderById(Long  id);

    PurchaseOrder createOrder(PurchaseOrder order, Long id);
    
    Page<PurchaseOrder> getOrders(Pageable pageable);

    Page<PurchaseOrder> getOrdersPerUser(Long id, Pageable pageable);
}
