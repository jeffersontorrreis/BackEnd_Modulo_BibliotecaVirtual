package com.portal.livros.dto;

import com.portal.livros.entities.Role;
import com.portal.livros.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Fone is required")
    private String fone;

    @NotBlank(message = "cpf is required")
    private String cpf;

    @NotBlank(message = "Adress is required")
    private String endereco;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private List<RoleDTO> roles = new ArrayList<>();

    public UsuarioDTO(){

    }

    public UsuarioDTO(Long id, String name, String email, String fone, String cpf, String endereco, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.fone = fone;
        this.cpf = cpf;
        this.endereco = endereco;
        this.password = password;
    }

    public UsuarioDTO(Usuario entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        fone = entity.getFone();
        cpf = entity.getCpf();
        endereco = entity.getEndereco();
        password = entity.getPassword();
        for (Role role: entity.getRoles()){
            roles.add(new RoleDTO(role));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
