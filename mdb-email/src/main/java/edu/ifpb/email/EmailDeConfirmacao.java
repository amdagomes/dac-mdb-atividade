/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.email;

import edu.ifpb.dac.Pedido;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

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
public class EmailDeConfirmacao implements MessageListener {

    @Override
    public void onMessage(Message message) {

        try {
            Pedido pedido = message.getBody(Pedido.class);

            if (message.propertyExists("email")) {
                if (message.getStringProperty("email").equals("aprovado")) {
                    System.out.println("Parabéns seu pedido"+ pedido.toString()+" foi Aprovado");
                } else {
                    System.out.println("Limite indisponivel");
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(EmailDeConfirmacao.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
