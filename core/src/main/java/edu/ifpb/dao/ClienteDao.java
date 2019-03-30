/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dao;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Pedido;
import edu.ifpb.interfaces.ClienteIF;
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
@Remote(ClienteIF.class)
public class ClienteDao implements ClienteIF {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void salvar(Cliente c) {
        em.persist(c);
    }
    
    @Override
    public Cliente Buscar(int id) {
        Cliente c = em.find(Cliente.class, id);
        return c;
    }
    
    @Override
    public List<Cliente> listar() {
        String sql = "SELECT p FROM Cliente c";
        TypedQuery<Cliente> query = em.createQuery(sql, Cliente.class);
        return query.getResultList();
    }
    
    @Override
    public void remove(int id) {
        Cliente c = em.find(Cliente.class, id);
        em.remove(c);
    }
    
}
