package edu.ifpb.dac;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemDeVenda> produtos;

    @ManyToOne
    private Cliente cliente;
    
    private BigDecimal valorTotal;

    public Pedido(int id, List<ItemDeVenda> produtos, Cliente cliente) {
        this.id = id;
        this.produtos = produtos;
        this.cliente = cliente;
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
    private void valorFinal() {
        
        BigDecimal total = null ;
        for (int i = 0; i < produtos.size(); i++) {

        total = total.add(produtos.get(i).getSubtotal()) ;
        this.valorTotal=total;
        }
        
    }
}
