package com.portal.livros.dto;

import com.portal.livros.entities.Livro;

public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private Integer isbn;
    private String imgUrl;

    public LivroDTO(){

    }

    public LivroDTO(Long id, String titulo, String autor, Integer isbn, String imgUrl) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.imgUrl = imgUrl;
    }

    public LivroDTO(Livro livro) {
        id = livro.getId();
        titulo = livro.getTitulo();
        autor = livro.getAutor();
        isbn = livro.getIsbn();
        imgUrl = livro.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
