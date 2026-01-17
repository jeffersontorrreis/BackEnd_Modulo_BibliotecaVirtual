package com.portal.livros.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private EmprestimoStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Usuario client;

    @OneToMany(mappedBy = "id.emprestimo", cascade = CascadeType.ALL, orphanRemoval = true) //O cascade é ideal para salvarmos relação de pai e filho. O pai é o save do proprio "emprestimo", e o filho é o save da lista de "EmprestimoItem" que são os itens relacionados ao "emprestimo".
    private Set<EmprestimoItem> items = new HashSet<>();


    /*Instant representa o isntante atual no presente. LocalDate representa uma data que passou ou futura*/
    public Emprestimo(){

    }

    public Emprestimo(Long id, Instant dataEmprestimo, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal, EmprestimoStatus status, Usuario client) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.status = status;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Instant dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public EmprestimoStatus getStatus() {
        return status;
    }

    public void setStatus(EmprestimoStatus status) {
        this.status = status;
    }

    public Usuario getClient() {
        return client;
    }

    public void setClient(Usuario client) {
        this.client = client;
    }

    public Set<EmprestimoItem> getItems(){
        return items;
    }

    public List<Livro> getLivros(){
        return items.stream().map(x-> x.getLivro()).toList();
    }
}
