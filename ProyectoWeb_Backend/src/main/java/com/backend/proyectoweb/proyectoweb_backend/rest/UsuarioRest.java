package com.backend.proyectoweb.proyectoweb_backend.rest;

import java.util.ArrayList;
import java.util.List;

import com.backend.proyectoweb.proyectoweb_backend.anotations.isAdmin;
import com.backend.proyectoweb.proyectoweb_backend.anotations.isCustomerOrAdmin;
import com.backend.proyectoweb.proyectoweb_backend.dtos.UsuarioDato;
import com.backend.proyectoweb.proyectoweb_backend.model.UsuarioSys;
import com.backend.proyectoweb.proyectoweb_backend.service.IUsuarioService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Users")
public class UsuarioRest {
    
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ModelMapper mapper;

    @isCustomerOrAdmin
    @GetMapping("info")
    public UsuarioDato getInfo(@RequestParam(name="email") String email){

        return mapper.map(usuarioService.getUserInfo(email), UsuarioDato.class);
    }
    
    
    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDato createUser(@RequestBody UsuarioDato dto){
        UsuarioSys user = mapper.map(dto, UsuarioSys.class);

        return  mapper.map(usuarioService.createUser(user), UsuarioDato.class);
    }

    @isAdmin
    @GetMapping("{page}/{size}")
    public Page<UsuarioDato> getUsers(@PathVariable("page") int pagina, @PathVariable("size") int size){

        Pageable pageable = PageRequest.of(pagina, size, Sort.by("id"));

        Page<UsuarioSys> users = usuarioService.getUsers(pageable);

        List<UsuarioDato> res = new ArrayList<>(); 

        for (UsuarioSys user : users.getContent()){

            res.add(mapper.map(user, UsuarioDato.class));
            
        }
        return new PageImpl<>(res, pageable, res.size());
    }

    @isAdmin
    @PutMapping(value = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDato updateUser(@RequestBody UsuarioDato dto, @PathVariable Long id){

        UsuarioSys user = mapper.map(dto, UsuarioSys.class);

        usuarioService.updateUser(user, id);

        return dto;
    }

    @isAdmin
    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id){

        usuarioService.deleteUser(id);
    }
}
