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

    @GetMapping("/api/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/api/user")
    public ResponseEntity<IUserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.saveUser(userDTO));
    }

    @PostMapping("/api/role")
    public ResponseEntity<IRoleDTO> saveRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok().body(userService.saveRole(roleDTO));
    }

    @PostMapping("/api/role/addtouser")
    public ResponseEntity<IRoleDTO> addRoleToUser(@RequestBody RoleToUser roleToUser) {
        userService.addRoleToUser(roleToUser.getUsername() , ERole.valueOf(roleToUser.getRoleName()));
        return ResponseEntity.ok().build();
    }
}
