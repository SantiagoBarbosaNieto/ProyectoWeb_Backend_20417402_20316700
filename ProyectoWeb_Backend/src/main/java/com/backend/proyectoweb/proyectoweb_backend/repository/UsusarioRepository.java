package com.backend.proyectoweb.proyectoweb_backend.repository;

import java.util.List;
import java.util.Optional;

import com.backend.proyectoweb.proyectoweb_backend.model.UsuarioSys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsusarioRepository extends JpaRepository<UsuarioSys, Long>{
    
    Optional<UsuarioSys> findById(Long id);

    Optional<UsuarioSys> findByEmail(String email);

    List<UsuarioSys> findByFirstName(String firstName);

    @Query(value = "SELECT u FROM UsuarioSys u WHERE u.email=?1 AND u.password=?2")
    Optional<UsuarioSys> loginMethod(String email, String password);

}

