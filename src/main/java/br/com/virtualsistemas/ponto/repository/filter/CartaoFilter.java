package br.com.virtualsistemas.ponto.repository.filter;

import java.util.Date;

public class CartaoFilter {

    private String pis;

    private String nome;

    private Date dataPontoDe;

    private Date dataPontoAte;

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataPontoDe() {
        return dataPontoDe;
    }

    public void setDataPontoDe(Date dataPontoDe) {
        this.dataPontoDe = dataPontoDe;
    }

    public Date getDataPontoAte() {
        return dataPontoAte;
    }

    public void setDataPontoAte(Date dataPontoAte) {
        this.dataPontoAte = dataPontoAte;
    }
}
