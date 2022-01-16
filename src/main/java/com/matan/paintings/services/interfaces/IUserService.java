package com.matan.paintings.services.interfaces;

import com.matan.paintings.models.implemenatations.ERole;
import com.matan.paintings.models.implemenatations.UserDTO;
import com.matan.paintings.models.interfaces.IRoleDTO;
import com.matan.paintings.models.interfaces.IUserDTO;

import java.util.List;

public interface IUserService {
    IUserDTO saveUser(IUserDTO userDTO);
    IRoleDTO saveRole(IRoleDTO roleDTO);
    void addRoleToUser(String username, ERole roleName);
    IUserDTO getUser(String username);
    List<UserDTO> getUsers();
}
