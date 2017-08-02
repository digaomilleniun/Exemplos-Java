package br.com.rpires.v1.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;

/**
 * 
 */

/**
 * @author rpires
 * 
 * Consome apenas uma mensagem da fila
 *
 */
public class TesteConsumidor1Mensagem {

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

		Message message = consumer.receive();
		System.out.println("Recebendo msg: " + message);

		new Scanner(System.in).nextLine();

		session.close();
		conexao.close();
		context.close();
	}

}
