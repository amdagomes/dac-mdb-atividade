package edu.ifpb.dac;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Cliente
 */
public class ItemDeVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Produto produto;

    private int quant;

    private BigDecimal subtotal;

    public ItemDeVenda() {
    }

    public ItemDeVenda(Produto produto, int quant) {
        this.produto = produto;
        this.quant = quant;
        this.subtotal = calculoSub();
    }

    public BigDecimal getSubtotal() {
        this.subtotal = calculoSub();
        return this.subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    private BigDecimal calculoSub() {
        Integer intege = 0;
        quant = intege.intValue();
        BigDecimal valor = new BigDecimal(quant);
        return produto.getPreco().multiply(valor);
    }

}
