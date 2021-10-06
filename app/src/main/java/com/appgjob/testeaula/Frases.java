package com.appgjob.testeaula;

import java.io.Serializable;

public class Frases implements Serializable  {

    public String pergunta;
    public String resposta1;
    public String resposta2;
    public String resposta3;
    public String resposta4;
    public String orientacao;


    public Frases(String pergunta, String resposta1, String resposta2, String resposta3, String resposta4, String orientacao) {
        this.pergunta = pergunta;
        this.resposta1 = resposta1;
        this.resposta2 = resposta2;
        this.resposta3 = resposta3;
        this.resposta4 = resposta4;
        this.orientacao = orientacao;
    }

    @Override
    public String toString(){
        return "Pergunta: " + pergunta +
                "\n\rResposta-1: " + resposta1 +
                "\n\rResposta-2: " + resposta2 +
                "\n\rResposta-3: " + resposta3 +
                "\n\rResposta-4: " + resposta4 ;
    }


    public String getPergunta() {
        return pergunta;
    }

    public String getResposta1() {
        return resposta1;
    }

    public String getResposta2() {
        return resposta2;
    }

    public String getResposta3() {
        return resposta3;
    }

    public String getResposta4() {
        return resposta4;
    }

    public String getOrientacao() {
        return orientacao;
    }
}
