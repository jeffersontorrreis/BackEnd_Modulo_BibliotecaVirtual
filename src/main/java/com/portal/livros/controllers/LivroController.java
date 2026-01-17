package com.portal.livros.controllers;

import com.portal.livros.dto.LivroDTO;
import com.portal.livros.dto.LivroDetailsDTO;
import com.portal.livros.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;


    @GetMapping
    public Page<LivroDTO> findAll(
            @RequestParam(name = "titulo", defaultValue = "") String titulo,
            @RequestParam(name = "autor", defaultValue = "") String autor, Pageable pageable){
        return livroService.findAll(titulo, autor, pageable);
    }

    @GetMapping(value = "/{id}")
    public LivroDetailsDTO findById( @PathVariable Long id){
        return livroService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public LivroDetailsDTO insert(@Valid @RequestBody LivroDetailsDTO dto){
        return livroService.insert(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public LivroDetailsDTO update(@PathVariable Long id, @Valid @RequestBody LivroDetailsDTO dto){
        dto = livroService.update(id, dto);
        return dto;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        livroService.delete(id);

    }
}
