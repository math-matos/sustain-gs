package br.com.fiap.empresasParceiras;

import java.time.LocalDate;

public class EmpresaParceira {
    private int id;
    private String nome;
    private String cnpj;
    private String senha;
    private String areaAtuacao;
    private LocalDate dataParceria;

    public EmpresaParceira() {}

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public EmpresaParceira(String nome, String cnpj, String senha, String areaAtuacao, LocalDate dataParceria) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.senha = senha;
        this.areaAtuacao = areaAtuacao;
        this.dataParceria = dataParceria;
    }

    //Getters e Setters
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public LocalDate getDataParceria() {
        return dataParceria;
    }

    public void setDataParceria(LocalDate dataParceria) {
        this.dataParceria = dataParceria;
    }
}
