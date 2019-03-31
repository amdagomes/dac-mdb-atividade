/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.validacao;

import edu.ifpb.dac.Pedido;
import edu.ifpb.dac.ValidarCartao;

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

/**
 *
 * @author Cliente
 */
@MessageDriven(
        mappedName = "jms/validarMsg",
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue")
            ,@ActivationConfigProperty(propertyName = "destinationLookup",
                    propertyValue = "jms/validarMsg")
            ,@ActivationConfigProperty(propertyName = "destination",
                    propertyValue = "queue")
        }
)
public class LerCompra implements MessageListener {

    
    private ValidarCartao valida= new ValidarCartao();
    @Inject
    private JMSContext context;
     @Resource(lookup = "jms/validarMsg")
    private Queue queue;
    @Override
    public void onMessage(Message msg) {

        try {
            JMSProducer producer = context.createProducer();

            String cartaoNumero = msg.getStringProperty("cartao");
            Pedido pedido = msg.getBody(Pedido.class);

            boolean validaCompra = valida.validarPedido(cartaoNumero, pedido);

            if (validaCompra) {

                producer.setProperty("aceito", true);
               
                
            } else {
                producer.setProperty("aceito", false);
                
            }
            
            producer.send(queue,"aceito");
            
        } catch (JMSException ex) {
            Logger.getLogger(LerCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}