package br.com.virtualsistemas.ponto.repository.cartao;

import br.com.virtualsistemas.ponto.model.Cartao;
import br.com.virtualsistemas.ponto.repository.filter.CartaoFilter;

import java.util.List;


public interface CartaoRepositoryQuery {

    List<Cartao> filtrar(CartaoFilter cartaoFilter);
}
