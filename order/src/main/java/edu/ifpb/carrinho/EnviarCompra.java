/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.carrinho;

import edu.ifpb.dac.Pedido;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

@Stateless

public class EnviarCompra {

    @Resource(lookup = "jms/validarMsg")
    private Queue queue;

    @Inject
    private JMSContext context;

    public void enviarPedido(String numeroCartao, Pedido pedido) {

        try {

            JMSProducer producer = context.createProducer();
            ObjectMessage createObjectMessage = context.createObjectMessage();

            createObjectMessage.setStringProperty("cartao", numeroCartao);
            createObjectMessage.setObjectProperty("pedido", pedido);

            producer.send(queue, createObjectMessage);

        } catch (JMSException ex) {
            Logger.getLogger(EnviarCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
