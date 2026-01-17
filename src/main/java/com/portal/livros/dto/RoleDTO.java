package com.portal.livros.dto;

import com.portal.livros.entities.Role;
import com.portal.livros.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {

    private Long id;
    private String authority;

    //private List<Usuario> users = new ArrayList<>();

    private RoleDTO(){

    }

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDTO(Role entity){
       id = entity.getId();
       authority = entity.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
