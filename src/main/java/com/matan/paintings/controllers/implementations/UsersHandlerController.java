package com.matan.paintings.controllers.implementations;

import com.matan.paintings.controllers.interfaces.IUsersHandlerController;
import com.matan.paintings.models.implemenatations.ERole;
import com.matan.paintings.models.implemenatations.RoleDTO;
import com.matan.paintings.models.implemenatations.RoleToUser;
import com.matan.paintings.models.implemenatations.UserDTO;
import com.matan.paintings.models.interfaces.IRoleDTO;
import com.matan.paintings.models.interfaces.IUserDTO;
import com.matan.paintings.services.implementations.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersHandlerController implements IUsersHandlerController {

    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/api/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @Override
    @PostMapping("/api/user")
    public ResponseEntity<IUserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.saveUser(userDTO));
    }

    @Override
    @PostMapping("/api/role")
    public ResponseEntity<IRoleDTO> saveRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok().body(userService.saveRole(roleDTO));
    }

    @Override
    @PostMapping("/api/role/addtouser")
    public ResponseEntity<IRoleDTO> addRoleToUser(@RequestBody RoleToUser roleToUser) {
        userService.addRoleToUser(roleToUser.getUsername() , ERole.valueOf(roleToUser.getRoleName()));
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/api/user/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "username") String username) {
        return null;
    }

    @Override
    @DeleteMapping("/api/role/{roleName}")
    public ResponseEntity<?> deleteRole(@PathVariable(value = "roleName") String roleName) {
        return null;
    }
}
