package com.portal.livros.dto;

import com.portal.livros.entities.EmprestimoItem;


public class HistoricoEmprestimoItemDTO {
    private Long livroId;
    private String titulo;
    private String imgUrl;
    private Integer quantidadeEmprestimo;
    private Integer quantidadeDevolvida;

    public HistoricoEmprestimoItemDTO(){

    }

    public HistoricoEmprestimoItemDTO(Long livroId, String titulo, String imgUrl, Integer quantidadeEmprestimo, Integer quantidadeDevolvida) {
        this.livroId = livroId;
        this.titulo = titulo;
        this.imgUrl = imgUrl;
        this.quantidadeEmprestimo = quantidadeEmprestimo;
        this.quantidadeDevolvida = quantidadeDevolvida;
    }

    public HistoricoEmprestimoItemDTO(EmprestimoItem entity){
        livroId = entity.getLivro().getId();
        titulo = entity.getLivro().getTitulo();
        imgUrl = entity.getLivro().getImgUrl();
        quantidadeEmprestimo = entity.getQuantidadeEmprestimo();
        quantidadeDevolvida = entity.getQuantidadeDevolvida();
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getQuantidadeEmprestimo() {
        return quantidadeEmprestimo;
    }

    public void setQuantidadeEmprestimo(Integer quantidadeEmprestimo) {
        this.quantidadeEmprestimo = quantidadeEmprestimo;
    }

    public Integer getQuantidadeDevolvida() {
        return quantidadeDevolvida;
    }

    public void setQuantidadeDevolvida(Integer quantidadeDevolvida) {
        this.quantidadeDevolvida = quantidadeDevolvida;
    }

}
