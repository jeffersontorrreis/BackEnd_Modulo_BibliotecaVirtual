package com.portal.livros.services;

import com.portal.livros.entities.Usuario;
import com.portal.livros.repositories.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioService usuarioService;

   /*
    public void validatelfOrAdmin(Long userId){
        Usuario me = usuarioService.authenticated();
        if(!me.hasRole("ROLE_ADMIN")){
            throw new ForbiddenException("Access denied");
        }
    }
   * */
}
