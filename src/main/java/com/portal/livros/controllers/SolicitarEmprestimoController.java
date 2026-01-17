package com.portal.livros.controllers;

import com.portal.livros.dto.SolicitarEmprestimoDTO;
import com.portal.livros.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/emprestimos")
public class SolicitarEmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PreAuthorize("hasAnyRole('ROLE_COMUN')")
    @PostMapping
    public SolicitarEmprestimoDTO insert(@RequestBody SolicitarEmprestimoDTO dto){
        return emprestimoService.insert(dto);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @PutMapping(value = "/{id}")
    public SolicitarEmprestimoDTO update(@PathVariable Long id, @RequestBody SolicitarEmprestimoDTO dto) {
        dto = emprestimoService.update(id, dto);
        return dto;
    }
}
