package com.portal.livros.services;

import com.portal.livros.dto.LivroDTO;
import com.portal.livros.dto.LivroDetailsDTO;
import com.portal.livros.entities.Emprestimo;
import com.portal.livros.entities.Livro;
import com.portal.livros.repositories.LivroRepository;
import com.portal.livros.repositories.exceptions.DatabaseException;
import com.portal.livros.repositories.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Transactional(readOnly = true)
    public Page<LivroDTO> findAll(String titulo, String autor, Pageable pageable){
        Page<Livro> result = livroRepository.searchByTituloandAutor(titulo, autor, pageable);
        return result.map(x-> new LivroDTO(x));
    }

    @Transactional(readOnly = true)
    public LivroDetailsDTO findById(Long id){
        try {
            Livro livro = livroRepository.findById(id).get();
            return new LivroDetailsDTO(livro);
        }
        catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Livro não encontrado!");
        }
    }

    @Transactional
    public LivroDetailsDTO insert (LivroDetailsDTO dto){
        Livro entity = new Livro();

        entity.setTitulo(dto.getTitulo());
        entity.setAutor(dto.getAutor());
        entity.setIsbn(dto.getIsbn());
        entity.setSinopse(dto.getSinopse());
        entity.setquantidadeDisponivel(dto.getQuantidadeDisponivel());
        entity.setImgUrl(dto.getImgUrl());

        entity = livroRepository.save(entity);

        return new LivroDetailsDTO(entity);
    }

    @Transactional
    public LivroDetailsDTO update (Long id,LivroDetailsDTO dto){
        try {
            Livro entity = livroRepository.findById(id).get();

            entity.setTitulo(dto.getTitulo());
            entity.setAutor(dto.getAutor());
            entity.setIsbn(dto.getIsbn());
            entity.setSinopse(dto.getSinopse());
            entity.setquantidadeDisponivel(dto.getQuantidadeDisponivel());
            entity.setImgUrl(dto.getImgUrl());

            entity = livroRepository.save(entity);

            return new LivroDetailsDTO(entity);
        }
        catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Livro não encontrado!");
        }
    }

    @Transactional
    public void delete (Long id) {
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }

        try {
            livroRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha na Integridade Referencial");
        }
    }
}
