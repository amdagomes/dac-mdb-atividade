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
import javax.jms.Topic;

@Stateless

public class EnviarCompra {

    @Resource(lookup = "jms/validarMsg")
    private Topic topic;
    @Inject
    private JMSContext context;

    public void enviarPedido(Pedido pedido) {

        try {

            JMSProducer producer = context.createProducer();
            ObjectMessage createObjectMessage = context.createObjectMessage();
            createObjectMessage.setObjectProperty("pedido", pedido);
            producer.send(topic, createObjectMessage);

        } catch (JMSException ex) {
            Logger.getLogger(EnviarCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
