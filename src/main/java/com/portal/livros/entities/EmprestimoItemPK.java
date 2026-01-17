package com.portal.livros.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class EmprestimoItemPK {

    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    public EmprestimoItemPK(){

    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}

