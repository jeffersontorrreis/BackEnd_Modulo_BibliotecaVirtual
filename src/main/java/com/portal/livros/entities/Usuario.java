package com.portal.livros.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String fone;
    private String cpf;
    private String endereco;
    private String password;

    @OneToMany(mappedBy = "client")
    private List<Emprestimo> emprestimoList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tb_usuario_role",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Usuario(){

    }

    public Usuario(Long id, String name, String email, String fone, String cpf, String endereco, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.fone = fone;
        this.cpf = cpf;
        this.endereco = endereco;
        this.password = password;
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

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Emprestimo> getEmprestimoList() {
        return emprestimoList;
    }

    public Set<Role> getRoles() {
        return roles;
    }



    public boolean hasRole(String roleName){
        for (Role role : roles){
            if(role.getAuthority().equals(roleName)){ /*Se algum dos roles do meu usuario for igual a esse que chegou "roleName" retorna true*/
                return true;
            }
        }
        return false;
    }


    public void addRole(Role role) {
        roles.add(role); /*Recebe um role(perfil) como parametro e adiciona no conjunto de roles(perfis)*/
    }



    /*equals e hascode normal que criamos para comparação de ids*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario user = (Usuario) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

