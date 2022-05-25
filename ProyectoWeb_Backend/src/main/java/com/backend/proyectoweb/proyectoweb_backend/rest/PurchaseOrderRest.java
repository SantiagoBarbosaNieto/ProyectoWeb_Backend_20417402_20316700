package com.backend.proyectoweb.proyectoweb_backend.rest;

import java.util.ArrayList;
import java.util.List;

import com.backend.proyectoweb.proyectoweb_backend.anotations.isAdmin;
import com.backend.proyectoweb.proyectoweb_backend.anotations.isCustomer;
import com.backend.proyectoweb.proyectoweb_backend.anotations.isCustomerOrAdmin;
import com.backend.proyectoweb.proyectoweb_backend.dtos.PurchaseOrderDTO;
import com.backend.proyectoweb.proyectoweb_backend.model.PurchaseOrder;
import com.backend.proyectoweb.proyectoweb_backend.service.IPurchaseOrderService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("PurchaseOrders")
public class PurchaseOrderRest {
    
    @Autowired
    private IPurchaseOrderService ordenCompraService;

    @Autowired
    private ModelMapper mapper;

    @isCustomerOrAdmin
    @GetMapping("{page}/{size}")
    public Page<PurchaseOrderDTO> getPurchaseOrders(@PathVariable("page") int pagina, @PathVariable("size") int size){

        Pageable pageable = PageRequest.of(pagina, size, Sort.by("id"));

        Page<PurchaseOrder> orders = ordenCompraService.getOrders(pageable);

        List<PurchaseOrderDTO> res = new ArrayList<>();

        for (PurchaseOrder order : orders.getContent()){

            res.add(mapper.map(order, PurchaseOrderDTO.class));

        }
        return new PageImpl<>(res, pageable, res.size());
    }

    @isCustomer
    @PostMapping(value = "create/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public PurchaseOrderDTO createPurchaseOrder(@RequestBody PurchaseOrderDTO dto, @PathVariable("id")Long id){

        PurchaseOrder order = mapper.map(dto, PurchaseOrder.class);

        return mapper.map(ordenCompraService.createOrder(order, id), PurchaseOrderDTO.class);

    }

    @isAdmin
    @PutMapping(value = "actualizar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PurchaseOrderDTO uOrderDTO(@RequestBody PurchaseOrderDTO dto, @PathVariable Long id){

        PurchaseOrder order = mapper.map(dto, PurchaseOrder.class);

        ordenCompraService.updateOrder(order, id);

        return dto;
    }

    @isAdmin
    @DeleteMapping("delete/{id}")
    public void deleteOrder(@PathVariable Long id){
        ordenCompraService.deletePurchaseOrder(id);
    }

    @isCustomerOrAdmin
    @GetMapping("purchase/per/user/{id}/{page}/{size}")
    public Page<PurchaseOrderDTO> purchasesPerUser(@PathVariable("id") Long id, @PathVariable("page") int page,
                                                   @PathVariable("size") int size){

        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        Page<PurchaseOrder> orders = ordenCompraService.getOrdersPerUser(id, pageable);

        List<PurchaseOrderDTO> res = new ArrayList<>();

        for (PurchaseOrder order : orders.getContent()) {

            res.add(mapper.map(order, PurchaseOrderDTO.class));

        }

        return new PageImpl<>(res, pageable,res.size());
    }
}