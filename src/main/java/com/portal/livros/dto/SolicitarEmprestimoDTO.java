package com.portal.livros.dto;

import com.portal.livros.entities.Emprestimo;
import com.portal.livros.entities.EmprestimoItem;
import com.portal.livros.entities.EmprestimoStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitarEmprestimoDTO {

    private Long id;
    private Instant dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private EmprestimoStatus status;

    private List<SolicitarEmprestimoItemDTO> items = new ArrayList<>();

    public SolicitarEmprestimoDTO(){

    }

    public SolicitarEmprestimoDTO(Long id, Instant dataEmprestimo, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal, EmprestimoStatus status) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.status = status;
    }

    public SolicitarEmprestimoDTO(Emprestimo entity){
        id = entity.getId();
        dataEmprestimo = entity.getDataEmprestimo();
        dataDevolucaoPrevista = entity.getDataDevolucaoPrevista();
        dataDevolucaoReal = entity.getDataDevolucaoReal();
        status = entity.getStatus();

        for (EmprestimoItem item : entity.getItems()){
            SolicitarEmprestimoItemDTO itemDTO = new SolicitarEmprestimoItemDTO(item);
            items.add(itemDTO);
            /*recuperando os items(produtos) e add.*/
        }
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

    public List<SolicitarEmprestimoItemDTO> getItems() {
        return items;
    }

    public void setItems(List<SolicitarEmprestimoItemDTO> items) {
        this.items = items;
    }
}
