package com.matan.paintings.models.interfaces;

import com.matan.paintings.models.implemenatations.RoleDTO;

import java.util.Set;

public interface IUserDTO {
    public String getId();

    public void setId(String id);

    public String getUsername();

    public void setUsername(String username);

    public String getEmail();

    public void setEmail(String email);

    public String getPassword();

    public void setPassword(String password);

    public Set<RoleDTO> getRoles();

    public void setRoles(Set<RoleDTO> roles);
}
