package br.com.virtualsistemas.ponto.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_codigo")
    @JsonProperty(value = "codigo")
    private Long codigo;

    @Column(name = "card_titulo")
    @JsonProperty(value = "titulo")
    private String titulo;

    @Column(name = "card_aparelho")
    @JsonProperty(value = "aparelho")
    private String aparelho;

    @Column(name = "card_numero_fabricacao")
    @JsonProperty(value = "numeroFabricacao")
    private String numeroFab;

    @Column(name = "card_empresa")
    @JsonProperty(value = "empresa")
    private String empresa;

    @Column(name = "card_cnpj")
    @JsonProperty(value = "cnpj")
    private String cnpj;

    @Column(name = "card_endereco")
    @JsonProperty(value = "local")
    private String local;

    @Column(name = "card_funcionario")
    @JsonProperty(value = "nome")
    private String nome;

    @Column(name = "card_pis")
    @JsonProperty(value = "pis")
    private String pis;

    @Column(name = "card_data_ponto")
    @JsonProperty(value = "data")
    private String DataPonto;

    @Column(name = "card_hora_ponto")
    @JsonProperty(value = "hora")
    private String HoraPonto;

    @Column(name = "card_assinatura_parte1")
    @JsonProperty(value = "assinaturaParte1")
    private String assinatura1;

    @Column(name = "card_assinatura_parte2")
    @JsonProperty(value = "assinaturaParte2")
    private String assinatura2;

    @Column(name = "card_assinatura_parte3")
    @JsonProperty(value = "assinaturaParte3")
    private String assinatura3;

    @Column(name = "card_nsr")
    @JsonProperty(value = "nsr")
    private String nsr;
}
