package br.com.virtualsistemas.ponto.service;

import br.com.virtualsistemas.ponto.model.Cartao;
import org.springframework.stereotype.Service;

@Service
public interface CartaoService {
    Cartao extrairDadosFoto(byte[] fotoCartao);

    Cartao salvarCartao(Cartao cartao);
}
