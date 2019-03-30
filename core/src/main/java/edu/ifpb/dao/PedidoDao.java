/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dao;

import edu.ifpb.interfaces.PedidoIF;
import edu.ifpb.dac.Pedido;
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
@Remote(PedidoIF.class)
public class PedidoDao implements PedidoIF {

    @PersistenceContext
    EntityManager em;

    @Override
    public void salvar(Pedido p) {
        em.persist(p);
    }

    @Override
    public Pedido Buscar(int id) {
        Pedido p = em.find(Pedido.class, id);
        return p;
    }

    @Override
    public List<Pedido> listar() {
        String sql = "SELECT p FROM Pedido p";
        TypedQuery<Pedido> query = em.createQuery(sql, Pedido.class);
        return query.getResultList();
    }

    @Override
    public void remove(int id) {
         Pedido p = em.find(Pedido.class, id);
         em.remove(p);
    }

}
