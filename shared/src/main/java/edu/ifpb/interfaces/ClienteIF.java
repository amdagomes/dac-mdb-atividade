/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.interfaces;

import edu.ifpb.dac.Cliente;
import java.util.List;

/**
 *
 * @author Cliente
 */
public interface ClienteIF {

    public void salvar(Cliente c);

    public Cliente Buscar(int id);

    public List<Cliente> listar();

    public void remove(int id);
}
