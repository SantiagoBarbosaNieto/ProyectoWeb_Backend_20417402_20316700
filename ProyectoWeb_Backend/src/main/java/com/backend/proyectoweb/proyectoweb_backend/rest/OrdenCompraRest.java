package com.backend.proyectoweb.proyectoweb_backend.rest;

import java.util.ArrayList;
import java.util.List;

import com.backend.proyectoweb.proyectoweb_backend.anotations.isAdmin;
import com.backend.proyectoweb.proyectoweb_backend.anotations.isCustomer;
import com.backend.proyectoweb.proyectoweb_backend.anotations.isCustomerOrAdmin;
import com.backend.proyectoweb.proyectoweb_backend.dtos.OrdenCompraDato;
import com.backend.proyectoweb.proyectoweb_backend.model.OrdenCompra;
import com.backend.proyectoweb.proyectoweb_backend.service.IOrdenCompraService;

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
public class OrdenCompraRest {
    
    @Autowired
    private IOrdenCompraService ordenCompraService;

    @Autowired
    private ModelMapper mapper;

    @isCustomerOrAdmin
    @GetMapping("{page}/{size}")
    public Page<OrdenCompraDato> getPurchaseOrders(@PathVariable("page") int pagina, @PathVariable("size") int size){

        Pageable pageable = PageRequest.of(pagina, size, Sort.by("id"));

        Page<OrdenCompra> orders = ordenCompraService.getOrders(pageable);

        List<OrdenCompraDato> res = new ArrayList<>(); 

        for (OrdenCompra order : orders.getContent()){

            res.add(mapper.map(order, OrdenCompraDato.class));

        }
        return new PageImpl<>(res, pageable, res.size());
    }

    @isCustomer
    @PostMapping(value = "create/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public OrdenCompraDato createPurchaseOrder(@RequestBody OrdenCompraDato dto,@PathVariable("id")Long id){

        OrdenCompra order = mapper.map(dto, OrdenCompra.class);

        return mapper.map(ordenCompraService.createOrder(order, id), OrdenCompraDato.class);

    }

    @isAdmin
    @PutMapping(value = "actualizar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrdenCompraDato uOrderDTO(@RequestBody OrdenCompraDato dto, @PathVariable Long id){

        OrdenCompra order = mapper.map(dto, OrdenCompra.class);

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
    public Page<OrdenCompraDato> purchasesPerUser(@PathVariable("id") Long id, @PathVariable("page") int page,
    @PathVariable("size") int size){

        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        Page<OrdenCompra> orders = ordenCompraService.getOrdersPerUser(id, pageable);

        List<OrdenCompraDato> res = new ArrayList<>();

        for (OrdenCompra order : orders.getContent()) {

            res.add(mapper.map(order, OrdenCompraDato.class));

        }

        return new PageImpl<>(res, pageable,res.size());
    }
}