package com.backend.proyectoweb.proyectoweb_backend.service;


import com.backend.proyectoweb.proyectoweb_backend.model.UserSys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    UserSys getUserInfo(String email);
    
    void deleteUser(Long id);

    UserSys updateUser(UserSys user, Long id);

    UserSys getUserById(Long  id);

    UserSys createUser(UserSys user);
    
    Page<UserSys> getUsers(Pageable pageable);

}
