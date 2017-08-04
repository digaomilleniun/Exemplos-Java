package br.com.rpires.v1.jms.topico;

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
 * Consome apenas uma mensagem da fila e o mesmo deve estar online antes do servidor de mensagens recebe-lá.
 *
 */
public class TesteConsumidor1Topico {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection conexao = cf.createConnection();

		conexao.start();
		
		Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination topico = (Destination) context.lookup("MyTopic");
		MessageConsumer consumer = session.createConsumer(topico);

		Message message = consumer.receive();
		System.out.println("Recebendo mensagem do tópico: " + message);

		new Scanner(System.in).nextLine();

		session.close();
		conexao.close();
		context.close();
	}

}
