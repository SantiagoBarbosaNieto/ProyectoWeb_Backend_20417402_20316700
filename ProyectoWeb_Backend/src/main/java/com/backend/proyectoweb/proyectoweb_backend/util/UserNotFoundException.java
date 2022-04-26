package com.backend.proyectoweb.proyectoweb_backend.util;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
		super("Could not find User by id:" + id);
	}
	
	public UserNotFoundException(String identificacion) {
		super("Could not find User by identificacion:" + identificacion);
	}

	public UserNotFoundException(String email, String password) {
		super("Could not find User by email: " + email+" and password: "+password);
	}
}
