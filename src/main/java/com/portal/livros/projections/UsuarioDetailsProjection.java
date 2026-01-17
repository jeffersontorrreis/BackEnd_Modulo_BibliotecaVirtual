package com.portal.livros.projections;

public interface UsuarioDetailsProjection {
    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}
