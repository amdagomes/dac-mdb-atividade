/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac;

import java.math.BigDecimal;

/**
 *
 * @author Cliente
 */
public class ValidarLimite {

    private final BigDecimal limite = new BigDecimal("100.000");

    public ValidarLimite() {
    }

    public boolean validarLimite(Pedido pedido) {
        return pedido.getValorTotal().compareTo(limite) == -1;
    }

}
