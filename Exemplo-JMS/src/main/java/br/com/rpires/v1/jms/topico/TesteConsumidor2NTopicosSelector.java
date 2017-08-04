/**
 * 
 */
package br.com.rpires.v1.jms.topico;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;

/**
 * @author rpires
 * 
 * Trabalhando com selectores.
 * 
 *
 */
public class TesteConsumidor2NTopicosSelector {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection conexao = cf.createConnection();
		conexao.setClientID(TesteConsumidor2NTopicosSelector.class.getName());

		conexao.start();
		
		Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topico = (Topic) context.lookup("MyTopic");
		
		//ebook=false - Mensagem será recebida somente se o ebook for igual a false
		MessageConsumer consumer = 
				session.createDurableSubscriber(topico, TesteConsumidor2NTopicosSelector.class.getName(), "ebook=false", false);

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
