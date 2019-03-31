/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.interfaces;

import edu.ifpb.dac.Pedido;
import java.util.List;

/**
 *
 * @author Cliente
 */
public interface PedidoIF {

    public void salvar(Pedido p);

    public Pedido Buscar(int id);

    public List<Pedido> listar();

    public void remove(int id);
}
