/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.carrinho;

import edu.ifpb.dac.Produto;
import edu.ifpb.interfaces.CarrinhoIF;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

/**
 *
 * @author Cliente
 */

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 5)
@Remote(CarrinhoIF.class)
public class Carrinho implements CarrinhoIF {

    private List<Produto> produtos = new ArrayList<>();

    @Override
    public void adicionar(Produto produto) {
        this.produtos.add(produto);
    }

    @Override
    public List<Produto> produtos() {
        return Collections.unmodifiableList(this.produtos);
    }

    @Override
    public void remover(Produto produto) {
        this.produtos.remove(produto);
    }

    @Remove
    @Override
    public void finalizar() {
        System.out.println("--- Produtos ----");
        
    }

}
