package com.portal.livros.controllers;

import com.portal.livros.dto.HistoricoEmprestimoDTO;
import com.portal.livros.dto.HistoricoEmprestimoDetailsDTO;
import com.portal.livros.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/historicoemprestimoDetails")
public class HistoricoEmprestimoDetailsController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping
    public List<HistoricoEmprestimoDetailsDTO> findAll(
            @RequestParam(name = "cpf", defaultValue = "") String cpf){

        return  emprestimoService.findAll(cpf);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/{id}")
    public HistoricoEmprestimoDetailsDTO findById(@PathVariable Long id){
        return emprestimoService.findByIdDetails(id);
    }
}
