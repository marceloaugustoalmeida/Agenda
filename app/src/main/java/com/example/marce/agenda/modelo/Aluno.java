package com.example.marce.agenda.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

//JsonIgnoreProperties = Ignora todas as variaveis que não estão listadas aqui
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aluno implements Serializable {

    //@JsonProperty("idCliente")
    private String id;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;
    private Double nota;
    private String caminhoFoto;

    private int desativado;

    public Aluno() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String foto) {
        this.caminhoFoto = foto;
    }

    public int getDesativado() {
        return desativado;
    }

    public void setDesativado(int desativado) {
        this.desativado = desativado;
    }

    public boolean estaDesativado() {
        return desativado == 1;
    }
}
