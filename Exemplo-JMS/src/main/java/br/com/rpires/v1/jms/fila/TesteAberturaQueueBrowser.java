/**
 * 
 */
package br.com.rpires.v1.jms;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

/**
 * @author rpires
 *
 * O QueueBrowser somente abre a mensagem sem consumi-la
 *
 */
public class TesteAberturaQueueBrowser {

	@SuppressWarnings("rawtypes")
	public static void main(String args[]) throws Exception {
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection conexao = cf.createConnection();

		conexao.start();
		
		Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("MyQueue");
		QueueBrowser browser = session.createBrowser((Queue) fila);
		
		Enumeration msgs = browser.getEnumeration();
		while (msgs.hasMoreElements()) { 
		    TextMessage msg = (TextMessage) msgs.nextElement(); 
		    System.out.println("Message: " + msg.getText()); 
		}
	}
}
