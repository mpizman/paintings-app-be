package com.matan.paintings.services.implementations;

import com.matan.paintings.models.implemenatations.ERole;
import com.matan.paintings.models.implemenatations.RoleDTO;
import com.matan.paintings.models.implemenatations.UserDTO;
import com.matan.paintings.models.interfaces.IRoleDTO;
import com.matan.paintings.models.interfaces.IUserDTO;
import com.matan.paintings.repository.RoleRepository;
import com.matan.paintings.repository.UserRepository;
import com.matan.paintings.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userRepository.findByUsername(username).orElse(null);
        if(userDTO == null) {
            throw new UsernameNotFoundException("user not found");
        }
        Collection<SimpleGrantedAuthority> auths = userDTO.getRoles().stream().map(roleDTO ->
                new SimpleGrantedAuthority(roleDTO.getName().name())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(userDTO.getUsername(), userDTO.getPassword(), auths);
    }

    @Override
    public IUserDTO saveUser(IUserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save((UserDTO) userDTO);
    }

    @Override
    public IRoleDTO saveRole(IRoleDTO roleDTO) {
        return roleRepository.save((RoleDTO) roleDTO);
    }

    @Override
    public void addRoleToUser(String username, ERole roleName) {
        UserDTO userDTO = userRepository.findByUsername(username).orElse(null);
        RoleDTO roleDTO = roleRepository.findByName(roleName).orElse(null);
        if (userDTO == null || roleDTO == null){
            return;
        }
        userDTO.getRoles().add(roleDTO);
        userRepository.save(userDTO);
    }

    @Override
    public IUserDTO getUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll();
    }
}
