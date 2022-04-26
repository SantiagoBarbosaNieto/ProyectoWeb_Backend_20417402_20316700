package com.backend.proyectoweb.proyectoweb_backend.util;

@SuppressWarnings("serial")
public class ShoppingCartNotFoundException extends RuntimeException {
    
    public ShoppingCartNotFoundException(Long id) {
		super("Could not find Shoping cart by id:" + id);
	}
	
	public ShoppingCartNotFoundException(String identificacion) {
		super("Could not find Shoping cart by identificacion:" + identificacion);
	}
}
