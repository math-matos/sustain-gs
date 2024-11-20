package br.com.fiap.projetos;

import br.com.fiap.empresasParceiras.EmpresaParceira;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Projeto {
    private int id;
    private String nomeProjeto;
    private String tipoEnergia;
    private BigDecimal custo;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    private EmpresaParceira empresaParceira;

    public Projeto() {
    }

    public Projeto(String nomeProjeto, String tipoEnergia, BigDecimal custo, LocalDate dataInicio, LocalDate dataConclusao, EmpresaParceira empresaParceira) {
        this.nomeProjeto = nomeProjeto;
        this.tipoEnergia = tipoEnergia;
        this.custo = custo;
        this.dataInicio = dataInicio;
        this.dataConclusao = dataConclusao;
        this.empresaParceira = empresaParceira;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(String tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public EmpresaParceira getEmpresaParceira() {
        return empresaParceira;
    }

    public void setEmpresaParceira(EmpresaParceira empresaParceira) {
        this.empresaParceira = empresaParceira;
    }
}
