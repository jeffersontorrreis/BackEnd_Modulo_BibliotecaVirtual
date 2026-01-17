package com.portal.livros.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_emprestimo_item")
public class EmprestimoItem {

    @EmbeddedId
    private EmprestimoItemPK id = new EmprestimoItemPK();

    private Integer quantidadeEmprestimo;
    private Integer quantidadeDevolvida;

    public EmprestimoItem(){

    }

    public EmprestimoItem(Emprestimo emprestimo, Livro livro, Integer quantidadeEmprestimo, Integer quantidadeDevolvida){
        id.setEmprestimo(emprestimo);
        id.setLivro(livro);
        this.quantidadeEmprestimo = quantidadeEmprestimo;
        this.quantidadeDevolvida = quantidadeDevolvida;

        /*Veja que aqui acima estamos acessando as variaveis tanto da tebla emprestimo quanto da tebela livros. Isso significa que se fizermos no futuro o EmprestimoItemDTO vamos poder declarar no private varoaveis tanto de emprestimo quanto de livros.*/
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

    public Emprestimo getEmprestimo(){
        return id.getEmprestimo();
    }

    public void setEmprestimo(Emprestimo emprestimo){
        id.setEmprestimo(emprestimo);
    }

    public Livro getLivro(){
        return id.getLivro();
    }

    public void setLivro(Livro livro){
        id.setLivro(livro);
    }
}
