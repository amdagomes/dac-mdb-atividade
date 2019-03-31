package edu.ifpb.dac;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Ricardo Job
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemDeVenda> produtos;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    private BigDecimal valorTotal;

    public Pedido(int id, List<ItemDeVenda> produtos, Cliente cliente) {
        this.id = id;
        this.produtos = produtos;
        this.cliente = cliente;
        this.valorTotal = valorFinal();
    }

    public Pedido() {
        this.produtos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add(ItemDeVenda item) {
        this.produtos.add(item);
    }

    public void remove(ItemDeVenda item) {
        this.produtos.remove(item);
    }

    public List<ItemDeVenda> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ItemDeVenda> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        this.valorTotal = valorFinal();
        return valorTotal;
    }
    
    private BigDecimal valorFinal() {
        BigDecimal total = new BigDecimal(0);
        for (ItemDeVenda item : produtos) {
            total = total.add(item.getSubtotal());
        }
        return total;
    }
}
