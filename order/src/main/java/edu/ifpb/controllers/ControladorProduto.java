/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.controladores;

import edu.ifpb.dac.Produto;
import edu.ifpb.interfaces.ProdutoIF;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Cliente
 */
@Named
@RequestScoped
public class ControladorProduto implements Serializable {

    @EJB
    private ProdutoIF produtoif;

    private Produto produto = new Produto();

    public String adicionarProduto() {
        this.produtoif.salvar(this.produto);
        this.produto = new Produto();
        return null;
    }

    public List<Produto> todosOsProdutos() {
        return this.produtoif.listar();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
