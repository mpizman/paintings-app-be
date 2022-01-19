package com.matan.paintings.controllers.interfaces;

import com.matan.paintings.models.implemenatations.RoleDTO;
import com.matan.paintings.models.implemenatations.RoleToUser;
import com.matan.paintings.models.implemenatations.UserDTO;
import com.matan.paintings.models.interfaces.IRoleDTO;
import com.matan.paintings.models.interfaces.IUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUsersHandlerController {

    ResponseEntity<List<UserDTO>> getUsers();

    ResponseEntity<IUserDTO> saveUser(@RequestBody UserDTO userDTO);

    ResponseEntity<IRoleDTO> saveRole(@RequestBody RoleDTO roleDTO);

    ResponseEntity<IRoleDTO> addRoleToUser(@RequestBody RoleToUser roleToUser);

    ResponseEntity<?> deleteUser(@RequestBody String username);

    ResponseEntity<?> deleteRole(@RequestBody String roleName);
}
