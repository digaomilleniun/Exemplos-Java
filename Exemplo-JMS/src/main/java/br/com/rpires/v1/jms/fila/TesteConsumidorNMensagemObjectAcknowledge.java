/**
 * 
 */
package br.com.rpires.v1.jms.fila;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.InitialContext;

import br.com.rpires.v1.jms.modelo.Pedido;

/**
 * @author rpires
 * 
 * Cria um listner para consumir todas as mensagens da fila
 *
 */
public class TesteConsumidorNMensagemObjectAcknowledge {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection conexao = cf.createConnection();

		conexao.start();
		//CLIENT_ACKNOWLEDGE necessita que você programa a confirmação de recebimento da mensagem.
		Session session = conexao.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("ActiveMQ.DLQ");
		MessageConsumer consumer = session.createConsumer(fila);

		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				ObjectMessage msg = (ObjectMessage) message;
				try {
					//Programa o recebimento da mensagem.
					message.acknowledge();
					Pedido pedido = (Pedido) msg.getObject();
					System.out.println(pedido.getCodigo());
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
