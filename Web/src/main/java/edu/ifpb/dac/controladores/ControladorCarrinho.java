/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.controladores;

import edu.ifpb.dac.Produto;
import edu.ifpb.interfaces.CarrinhoIF;
import edu.ifpb.interfaces.ProdutoIF;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cliente
 */
public class ControladorCarrinho {

    @EJB
    private CarrinhoIF carrinho;
    private Produto produto;

    @EJB
    private ProdutoIF dao;

    public List<Produto> listarCarrinho() {
        return carrinho.produtos();
    }

    public List<Produto> listarTodosProdutos() {
        return dao.listar();
    }

    public void adicionar() {
        carrinho.adicionar(this.produto);
    }

    public void remover(Produto p) {
        carrinho.remover(p);
    }

    public String finalizar() {
        this.carrinho.finalizar();
        finalizarSessao();
        return null;
    }

    private void finalizarSessao() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.invalidate();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
