/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.validacao;

import edu.ifpb.dac.Pedido;

import edu.ifpb.dac.ValidarLimite;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 *
 * @author Cliente
 */
@MessageDriven(
        mappedName = "jms/validarMsg",
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Topic")
            ,@ActivationConfigProperty(propertyName = "destinationLookup",
                    propertyValue = "jms/validarMsg")
            ,@ActivationConfigProperty(propertyName = "destination",
                    propertyValue = "topic")
        }
)
public class LerCompra implements MessageListener {

    private ValidarLimite valida = new ValidarLimite();
    @Inject
    private JMSContext context;
    @Resource(lookup = "jms/validarMsg")
    private Topic topic;

    @Override
    public void onMessage(Message msg) {

        try {
            JMSProducer producer = context.createProducer();

            Pedido pedido = msg.getBody(Pedido.class);

            if (valida.validarLimite(pedido)) {

                producer.setProperty("aprovado", true);

            } else {
                producer.setProperty("aprovado", false);

            }
            producer.setProperty("email", "aprovado");
            producer.send(topic, pedido);

        } catch (JMSException ex) {
            Logger.getLogger(LerCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
