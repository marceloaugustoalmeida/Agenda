package com.example.marce.agenda.modelo;

import java.io.Serializable;
import java.util.List;

public class Prova implements Serializable {

    private String mateira;

    public String getMateira() {
        return mateira;
    }

    public void setMateira(String mateira) {
        this.mateira = mateira;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    private String data;
    private List<String> topicos;

    public Prova(String mateira, String data, List<String> topicos) {
        this.mateira = mateira;
        this.data = data;
        this.topicos = topicos;
    }

    @Override
    public String toString() {
        return this.getMateira();
    }
}
