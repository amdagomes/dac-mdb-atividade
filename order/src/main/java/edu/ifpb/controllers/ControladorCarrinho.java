/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.controllers;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.ItemDeVenda;
import edu.ifpb.dac.Pedido;
import edu.ifpb.dac.Produto;
import edu.ifpb.interfaces.CarrinhoIF;
import edu.ifpb.interfaces.ProdutoIF;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cliente
 */
@Named
@SessionScoped
public class ControladorCarrinho implements Serializable{

    @EJB
    private CarrinhoIF carrinho;
    private Cliente cliente = new Cliente();
    private ItemDeVenda item = new ItemDeVenda();
    private Pedido pedido = new Pedido();

    public List<ItemDeVenda> listarCarrinho() {
        return carrinho.itens();
    }
    
    public BigDecimal totalPedido(){
        return carrinho.total();
    }
    
    public int quantProdutos(){
        return carrinho.quantProdutos();
    }

    public void adicionar(Produto produto) {
        this.item.setProduto(produto);
        carrinho.adicionar(item);
        this.item = new ItemDeVenda();
    }

    public void remover(ItemDeVenda item) {
        carrinho.remover(item);
    }

    public String finalizarPedido() {
        this.pedido.setCliente(this.cliente);
        this.carrinho.finalizar(pedido);
        finalizarSessao();
        return "index.xhtml";
    }

    private void finalizarSessao() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.invalidate();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public ItemDeVenda getItem() {
        return item;
    }

    public void setItem(ItemDeVenda item) {
        this.item = item;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    
}
