package br.com.virtualsistemas.ponto.repository.cartao;

import br.com.virtualsistemas.ponto.model.Cartao;
import br.com.virtualsistemas.ponto.model.Cartao_;
import br.com.virtualsistemas.ponto.repository.filter.CartaoFilter;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CartaoRepositoryImpl implements CartaoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cartao> filtrar(CartaoFilter cartaoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Cartao> criteria = builder.createQuery(Cartao.class);
        Root<Cartao> root = criteria.from(Cartao.class);

        Predicate[] predicates = criarRestricoes(cartaoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Cartao> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] criarRestricoes(CartaoFilter cartaoFilter, CriteriaBuilder builder,
                                        Root<Cartao> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(cartaoFilter.getPis())) {
            predicates.add(builder.equal(
                    builder.lower(root.get(Cartao_.PIS)), cartaoFilter.getPis().toLowerCase()));
        }

        if (!StringUtils.isEmpty(cartaoFilter.getNome())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Cartao_.NOME)), "%" + cartaoFilter.getNome().toLowerCase() + "%"));
        }

        if (cartaoFilter.getDataPontoDe() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(Cartao_.DATA_PONTO), cartaoFilter.getDataPontoDe()));
        }

        if (cartaoFilter.getDataPontoAte() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(Cartao_.DATA_PONTO), cartaoFilter.getDataPontoAte()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
