/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.controladores;

import edu.ifpb.dac.ItemDeVenda;
import edu.ifpb.dac.Pedido;
import edu.ifpb.interfaces.PedidoIF;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Cliente
 */
@Named
@SessionScoped
public class ControladorPedido implements Serializable {

    @EJB
    private PedidoIF dao;
    private Pedido p = new Pedido();
    private ItemDeVenda item = new ItemDeVenda();

    public void adicionar() {
        dao.salvar(this.p);
    }

    public String addItemVenda() {
        this.p.getProdutos().add(this.item);
        this.item = new ItemDeVenda();
        return null;
    }

    public String removeItemVenda(ItemDeVenda itemDeVenda) {
        this.p.getProdutos().remove(itemDeVenda);
        return null;
    }

    public Pedido getP() {
        return p;
    }

    public void setP(Pedido p) {
        this.p = p;
    }

    public ItemDeVenda getItem() {
        return item;
    }

    public void setItem(ItemDeVenda item) {
        this.item = item;
    }

}
