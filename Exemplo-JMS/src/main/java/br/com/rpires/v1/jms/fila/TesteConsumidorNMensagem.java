/**
 * 
 */
package br.com.rpires.v1.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

/**
 * @author rpires
 * 
 * Cria um listner para consumir todas as mensagens da fila
 *
 */
public class TesteConsumidorNMensagem {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection conexao = cf.createConnection();

		conexao.start();
		
		Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("MyQueue");
		MessageConsumer consumer = session.createConsumer(fila);

		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage msg = (TextMessage) message;
				try {
					System.out.println(msg.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		new Scanner(System.in).nextLine();

		session.close();
		conexao.close();
		context.close();
	}

}
