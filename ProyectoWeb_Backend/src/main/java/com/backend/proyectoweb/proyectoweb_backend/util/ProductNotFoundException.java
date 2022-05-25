package com.backend.proyectoweb.proyectoweb_backend.util;

@SuppressWarnings("serial")
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
		super("Could not find Product by id:" + id);
	}
	
	public ProductNotFoundException(String identificacion) {
		super("Could not find Product by identificacion:" + identificacion);
	}
    
}
