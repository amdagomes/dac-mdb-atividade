package edu.ifpb.dac;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
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
@Entity
public class ItemDeVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Produto produto;

    private int quant;

    private BigDecimal subtotal;

    public ItemDeVenda() {
        this.quant = 1;
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
        BigDecimal valor = new BigDecimal(quant);
        return produto.getPreco().multiply(valor);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.produto);
        hash = 67 * hash + this.quant;
        hash = 67 * hash + Objects.hashCode(this.subtotal);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemDeVenda other = (ItemDeVenda) obj;
        if (this.quant != other.quant) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.subtotal, other.subtotal)) {
            return false;
        }
        return true;
    }

}
