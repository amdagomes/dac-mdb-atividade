/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.carrinho;

import edu.ifpb.dac.ItemDeVenda;
import edu.ifpb.dac.Pedido;
import edu.ifpb.dac.Produto;
import edu.ifpb.interfaces.CarrinhoIF;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
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
//@Remote(CarrinhoIF.class)
public class Carrinho implements CarrinhoIF {
  @EJB
  private EnviarCompra validar;
    private Pedido pedido = new Pedido();
//    private List<ItemDeVenda> itens = new ArrayList<>();

    @Override
    public void adicionar(ItemDeVenda item) {
        System.out.println("produto: " + item.getProduto().getDescricao());
        System.out.println("quantidade: " + item.getQuant());
        this.pedido.getProdutos().add(item);
//        this.itens.add(item);
    }

    @Override
    public List<ItemDeVenda> itens() {
//        return Collections.unmodifiableList(this.itens);
        return Collections.unmodifiableList(this.pedido.getProdutos());
    }

    @Override
    public void remover(ItemDeVenda item) {
//        this.itens.remove(item);
        this.pedido.getProdutos().remove(item);
    }

    @Remove
    @Override
    public void finalizar( String numeroCartao,Pedido p) {
        validar.enviarPedido(numeroCartao, pedido);
        
        System.out.println("--- Produtos ----");
        
    }
    
    @Override
    public int quantProdutos(){
//        return this.itens.size();
        return this.pedido.getProdutos().size();
    }
    
    @Override
    public BigDecimal total(){
        return this.pedido.getValorTotal();
    }

}
