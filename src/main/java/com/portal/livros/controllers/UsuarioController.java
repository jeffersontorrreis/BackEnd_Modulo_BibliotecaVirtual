package com.portal.livros.controllers;
import com.portal.livros.dto.UsuarioDTO;
import com.portal.livros.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public UsuarioDTO insert(@Valid @RequestBody UsuarioDTO dto){
        return service.insert(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_COMUN', 'ROLE_ADMIN')")
    @PutMapping
    public UsuarioDTO update(@Valid @RequestBody UsuarioDTO dto){
        return service.update(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_COMUN', 'ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/me")
    public UsuarioDTO getMe(){
        UsuarioDTO dto = service.getMe();
        return dto;
    }

}