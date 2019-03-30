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
public class ValidarCartao {

    private final String cartaoValidado = "12345678910";
    private final BigDecimal limite = new BigDecimal("100.000");

    public ValidarCartao() {
        
        
    }

    public boolean validarPedido(String cartaoNumero, BigDecimal valorCompra) {
       if(validarCartao(cartaoNumero) ==true &&validarLimite(valorCompra)==true){
        return true;
       
       
       }
       return false;
    }

    public boolean validarCartao(String cartaoNumero) {
        if (cartaoNumero == cartaoValidado) {
            return true;

        } else {
            return false;
        }
    }

    public boolean validarLimite(BigDecimal valorCompra) {
        if (valorCompra.compareTo(limite) == -1) {
            return true;

        } else {
            return false;
        }
    }

}
