package br.com.rpires.v1.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

/**
 * 
 */

/**
 * @author rpires
 *
 * Envia 100 mensagens para o servidor de mensagens
 *
 */
public class TesteProdutorMensagem {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection conexao = cf.createConnection();

		conexao.start();
		
		Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("MyQueue");
		
		MessageProducer producer = session.createProducer(fila);
		
		for (int i = 0; i <= 100; i++) {
			Message msg = session.createTextMessage("Enviando mensagem numero:" + i);
			producer.send(msg);
		}

		new Scanner(System.in).nextLine();

		session.close();
		conexao.close();
		context.close();
	}

}
