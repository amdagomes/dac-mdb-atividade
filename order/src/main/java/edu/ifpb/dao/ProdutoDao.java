package edu.ifpb.dao;


import edu.ifpb.dac.Produto;
import edu.ifpb.interfaces.ProdutoIF;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Cliente
 */
@Stateless
//@Remote(ProdutoIF.class)
@Local(ProdutoIF.class)
public class ProdutoDao implements ProdutoIF {

    @PersistenceContext
    EntityManager em;

    @Override
    public void salvar(Produto produto) {
        em.persist(produto);
    }

    @Override
    public Produto buscar(long id) {
        Produto prod = em.find(Produto.class, id);
        return prod;

    }

    @Override
    public List<Produto> listar() {
        String querySql = "SELECT p FROM Produto p ";
        TypedQuery<Produto> query = em
                .createQuery(querySql, Produto.class);
        if (query.getResultList() == null) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }

}
