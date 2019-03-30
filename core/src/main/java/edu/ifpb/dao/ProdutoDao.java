/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dao;


import edu.ifpb.interfaces.ProdutoIF;
import edu.ifpb.dac.Produto;
import java.util.List;
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
@Remote(ProdutoIF.class)
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
        String sql = "SELECT p FROM Produto p";
        TypedQuery<Produto> query = em.createQuery(sql, Produto.class);
        return query.getResultList();
    }

}
