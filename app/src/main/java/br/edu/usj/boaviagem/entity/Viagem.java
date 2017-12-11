package br.edu.usj.boaviagem.entity;
import android.content.pm.InstrumentationInfo;

import java.io.Serializable;
import java.util.Date;


public class Viagem implements Serializable{


    private int id;
    private String destino;
    private String tipoViagem;
    private String dataSaida;
    private String dataChegada;
    private String orcamento;
    private int quantidade;

    public Viagem(){}

    public Viagem (int id, String destino, String tipoViagem, String dataChegada, String dataSaida, String orcamento, int quantidade){
        this.id = id;
        this.destino = destino;
        this.tipoViagem = tipoViagem;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.orcamento = orcamento;
        this.quantidade = quantidade;
    }


    public int getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public String getTipoViagem() {
        return tipoViagem;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public String getOrcamento() {
        return orcamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setTipoViagem(String tipoViagem) {
        this.tipoViagem = tipoViagem;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public void setOrcamento(String orcamento) {
        this.orcamento = orcamento;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
