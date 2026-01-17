package com.portal.livros.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private Integer isbn;
    private String sinopse;
    private Integer quantidadeDisponivel;
    private String imgUrl;

    public Livro(){

    }

    public Livro(Long id, String titulo, String autor, Integer isbn, String sinopse, Integer quantidadeDisponivel, String imgUrl) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.sinopse = sinopse;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.imgUrl = imgUrl;
    }

    @ManyToMany
    @JoinTable(name = "tb_livro_categoria",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias = new HashSet<>();

    @OneToMany(mappedBy = "id.livro")
    private Set<EmprestimoItem> items = new HashSet<>();


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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getquantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setquantidadeDisponivel(Integer quantidade) {
        this.quantidadeDisponivel = quantidade;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Set<EmprestimoItem> getItems(){
        return items;
    }

    public List<Emprestimo> getEmprestimos(){
        return items.stream().map(x-> x.getEmprestimo()).toList();
    }
}
