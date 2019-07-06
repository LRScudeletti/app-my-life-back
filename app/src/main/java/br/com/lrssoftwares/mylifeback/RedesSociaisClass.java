package br.com.lrssoftwares.mylifeback;

import java.util.Date;

class RedesSociaisClass {
    private int id;
    private String nome;
    private Date dia;
    private int hoje;
    private int mes;
    private int total;
    private int alertaAtivo;
    private int tempoAlerta;
    private Date ultimoAlerta;

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

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public int getHoje() {
        return hoje;
    }

    public void setHoje(int hoje) {
        this.hoje = hoje;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAlertaAtivo() {
        return alertaAtivo;
    }

    public void setAlertaAtivo(int alertaAtivo) {
        this.alertaAtivo = alertaAtivo;
    }

    public int getTempoAlerta() {
        return tempoAlerta;
    }

    public void setTempoAlerta(int tempoAlerta) {
        this.tempoAlerta = tempoAlerta;
    }

    public Date getUltimoAlerta() {
        return ultimoAlerta;
    }

    public void setUltimoAlerta(Date ultimoAlerta) {
        this.ultimoAlerta = ultimoAlerta;
    }
}