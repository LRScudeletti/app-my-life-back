package br.com.lrssoftwares.mylifeback;

class RedesSociaisClass {
    private int id;
    private String diaAtual;
    private int hoje;
    private int mes;
    private int total;
    private int alertaAtivo;
    private int tempoAlerta;

    private int notificouHoje;

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getDiaAtual() {
        return diaAtual;
    }

    void setDiaAtual(String diaAtual) {
        this.diaAtual = diaAtual;
    }

    int getHoje() {
        return hoje;
    }

    void setHoje(int hoje) {
        this.hoje = hoje;
    }

    int getMes() {
        return mes;
    }

    void setMes(int mes) {
        this.mes = mes;
    }

    int getTotal() {
        return total;
    }

    void setTotal(int total) {
        this.total = total;
    }

    int getAlertaAtivo() {
        return alertaAtivo;
    }

    void setAlertaAtivo(int alertaAtivo) {
        this.alertaAtivo = alertaAtivo;
    }

    int getTempoAlerta() {
        return tempoAlerta;
    }

    void setTempoAlerta(int tempoAlerta) {
        this.tempoAlerta = tempoAlerta;
    }

    int getNotificouHoje() {
        return notificouHoje;
    }

    void setNotificouHoje(int notificouHoje) {
        this.notificouHoje = notificouHoje;
    }
}