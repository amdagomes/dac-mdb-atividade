/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.interfaces;

import edu.ifpb.dac.Produto;
import java.util.List;

/**
 *
 * @author Cliente
 */
public interface ProdutoIF {

    public void salvar(Produto produto);

    public Produto buscar(long id);

    public List<Produto> listar();
}
