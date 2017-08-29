/**
 * 
 */
package br.com.rpires.v1.jms.fila;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

/**
 * @author Rodrigo Pires
 *
 */
public class TesteProdutorFilaLog {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection conexao = cf.createConnection();

		conexao.start();

		Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("MyQueue.Log");

		MessageProducer producer = session.createProducer(fila);

		for (int i = 0; i <= 1; i++) {
			Message msg = session.createTextMessage("Enviando mensagem numero:" + i);
			
			// Para utilizar o último parâmetro de prioridade é preciso alterar a configuração do servidor de fila.
			// conf/activemq.xml colocar em <destinationPolicy> <policyEntries>
			// <policyEntry queue=">" prioritizedMessages="true"/>
			producer.send(msg, DeliveryMode.PERSISTENT, 0, 5000);
		}

		new Scanner(System.in).nextLine();

		session.close();
		conexao.close();
		context.close();
	}
}
