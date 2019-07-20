package br.com.lrssoftwares.mylifeback;

class RedesSociaisClass {
    private int id;
    private int diaAtual;
    private int mesAtual;
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

    int getDiaAtual() {
        return diaAtual;
    }

    void setDiaAtual(int diaAtual) {
        this.diaAtual = diaAtual;
    }

    public int getMesAtual() {
        return mesAtual;
    }

    public void setMesAtual(int mesAtual) {
        this.mesAtual = mesAtual;
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