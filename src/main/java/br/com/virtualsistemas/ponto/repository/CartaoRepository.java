package br.com.virtualsistemas.ponto.repository;

import br.com.virtualsistemas.ponto.model.Cartao;
import br.com.virtualsistemas.ponto.repository.cartao.CartaoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long>, CartaoRepositoryQuery {

}
