package com.backend.proyectoweb.proyectoweb_backend.service;


import java.util.ArrayList;
import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.UserSys;
import com.backend.proyectoweb.proyectoweb_backend.repository.RoleRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.UsusarioRepository;
import com.backend.proyectoweb.proyectoweb_backend.util.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private RoleRepository rolesRepository;

    @Autowired
    private UsusarioRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void deleteUser(Long id) {

        Optional<UserSys> user = repository.findById(id);

        if(user.isPresent()){
            repository.delete(user.get());
        } else{
            throw new UserNotFoundException(id);
        }       
    
    }

    @Override
    public UserSys getUserById(Long id) {

        return repository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    
    }

    @Override
    public UserSys createUser(UserSys user) {
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRol(rolesRepository.getById(2l));
        user.setOrders(new ArrayList<>());
        return repository.save(user);

    }
    
    @Override
    public UserSys updateUser(UserSys user, Long id) {

        return repository.findById(id).map(provider ->{
            provider.setFirstName(user.getFirstName());
            provider.setLastName(user.getLastName());
            provider.setBirthDate(user.getBirthDate());
            provider.setEmail(user.getEmail());
            provider.setPassword(user.getPassword());
            provider.setPassword(user.getPassword());

            return repository.save(provider);
        }).orElseGet(()->{
            throw new UserNotFoundException(id);
        });
    }

    @Override
    public Page<UserSys> getUsers(Pageable pageable) {

        return repository.findAll(pageable);

    }

    @Override
    public UserSys getUserInfo(String email) {
        return repository.findByEmail(email).orElseThrow(()-> new UserNotFoundException(email));
    }
        
}
