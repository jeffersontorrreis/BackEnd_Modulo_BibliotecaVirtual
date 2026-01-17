package com.portal.livros.dto;

import com.portal.livros.entities.EmprestimoItem;

public class SolicitarEmprestimoItemDTO {

    private Long livroId;
    private Integer quantidadeEmprestimo;
    private Integer quantidadeDevolvida;

    public SolicitarEmprestimoItemDTO(){

    }

    public SolicitarEmprestimoItemDTO(Long livroId, Integer quantidadeEmprestimo, Integer quantidadeDevolvida) {
        this.livroId = livroId;
        this.quantidadeEmprestimo = quantidadeEmprestimo;
        this.quantidadeDevolvida = quantidadeDevolvida;
    }

    public SolicitarEmprestimoItemDTO(EmprestimoItem entity){
        livroId = entity.getLivro().getId();
        quantidadeEmprestimo = entity.getQuantidadeEmprestimo();
        quantidadeDevolvida = entity.getQuantidadeDevolvida();
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
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
