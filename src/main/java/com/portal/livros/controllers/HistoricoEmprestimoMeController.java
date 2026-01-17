package com.portal.livros.controllers;

import com.portal.livros.dto.HistoricoEmprestimoDTO;
import com.portal.livros.dto.HistoricoEmprestimoDetailsDTO;
import com.portal.livros.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Historicoemprestimome")
public class HistoricoEmprestimoMeController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PreAuthorize("hasAnyRole('ROLE_COMUN')")
    @GetMapping
    public List<HistoricoEmprestimoDTO> findAll(){

        return  emprestimoService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_COMUN')")
    @GetMapping(value = "/{id}")
    public HistoricoEmprestimoDTO findById(@PathVariable Long id){
        return emprestimoService.findById(id);
    }
}
