package br.com.lrssoftwares.mylifeback;

class RedesSociaisClass {
    private int id;
    private int dia;
    private String tempo;
    private int ativo;

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    int getDia() {
        return dia;
    }

    void setDia(int dia) {
        this.dia = dia;
    }

    String getTempo() {
        return tempo;
    }

    void setTempo(String tempo) {
        this.tempo = tempo;
    }

    int getAtivo() {
        return ativo;
    }

    void setAtivo(int ativo) {
        this.ativo = ativo;
    }
}