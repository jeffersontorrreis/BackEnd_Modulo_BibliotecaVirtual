package com.portal.livros.dto;

import com.portal.livros.entities.Livro;
import jakarta.validation.constraints.NotBlank;

public class LivroDetailsDTO {

    private Long id;

    @NotBlank(message = "Titulo is required")
    private String titulo;

    @NotBlank(message = "Autor is required")
    private String autor;

    @NotBlank(message = "isbn is required")
    private Integer isbn;

    @NotBlank(message = "Sinopse is required")
    private String sinopse;

    @NotBlank(message = "Quantidade is required")
    private Integer quantidadeDisponivel;

    @NotBlank(message = "Image is required")
    private String imgUrl;

    public LivroDetailsDTO(){

    }

    public LivroDetailsDTO(Long id, String titulo, String autor, Integer isbn, String sinopse, Integer quantidadeDisponivel, String imgUrl) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.sinopse = sinopse;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.imgUrl = imgUrl;
    }

    public LivroDetailsDTO(Livro livro){
        id = livro.getId();
        titulo = livro.getTitulo();
        autor = livro.getAutor();
        isbn = livro.getIsbn();
        sinopse = livro.getSinopse();
        quantidadeDisponivel = livro.getquantidadeDisponivel();
        imgUrl = livro.getImgUrl();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
