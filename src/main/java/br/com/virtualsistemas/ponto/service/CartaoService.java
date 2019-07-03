package br.com.virtualsistemas.ponto.service;

import br.com.virtualsistemas.ponto.model.Cartao;
import br.com.virtualsistemas.ponto.repository.filter.CartaoFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartaoService {
    Cartao extrairDadosFotoCartao(byte[] fotoCartao);
    Cartao salvarCartao(Cartao cartao);

    List<Cartao> filtrar(CartaoFilter lancamentoFilter);
}
