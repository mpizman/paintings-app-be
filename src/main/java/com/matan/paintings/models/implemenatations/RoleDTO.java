package com.matan.paintings.models.implemenatations;

import com.matan.paintings.models.interfaces.IRoleDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class RoleDTO implements IRoleDTO {
    @Id
    private String id;
    @Indexed(unique=true)
    private ERole name;

    public RoleDTO() {
    }

    public RoleDTO(ERole name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
