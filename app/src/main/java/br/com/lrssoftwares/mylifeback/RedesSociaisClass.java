package br.com.lrssoftwares.mylifeback;

class RedesSociaisClass {
    private int id;
    private String processo;
    private double tempo;
    private int ativo;

    RedesSociaisClass() {
    }

    RedesSociaisClass(int id, String processo, double tempo, int ativo) {
        this.id = id;
        this.processo = processo;
        this.tempo = tempo;
        this.ativo = ativo;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getProcesso() {
        return processo;
    }

    void setProcesso(String processo) {
        this.processo = processo;
    }

    double getTempo() {
        return tempo;
    }

    void setTempo(double tempo) {
        this.tempo = tempo;
    }

    int getAtivo() {
        return ativo;
    }

    void setAtivo(int ativo) {
        this.ativo = ativo;
    }
}