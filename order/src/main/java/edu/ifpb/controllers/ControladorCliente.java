/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.controllers;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Produto;
import edu.ifpb.interfaces.ClienteIF;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Cliente
 */
@Named
@RequestScoped
public class ControladorCliente implements Serializable {

    @EJB
    private ClienteIF dao;
    private Cliente cliente;

    public String adicionar() {
        this.dao.salvar(this.cliente);
        return null;
    }

    public String remover(Produto p) {
        this.dao.remove(this.cliente.getId());
        return null;

    }

    public ClienteIF getDao() {
        return dao;
    }

    public void setDao(ClienteIF dao) {
        this.dao = dao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}