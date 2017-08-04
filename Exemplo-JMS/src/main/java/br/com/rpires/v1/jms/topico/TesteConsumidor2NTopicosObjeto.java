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
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;

import br.com.rpires.v1.jms.modelo.Pedido;

/**
 * @author rpires
 * 
 * Cria um listner para consumir todas as mensagens da fila independente se a mensagem foi enviada antes ou depois desta classe ser inicializada.
 * 
 *
 */
public class TesteConsumidor2NTopicosObjeto {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		
		//Obrigatório para deserializar objetos. Package específico
		//System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","java.lang,br.com.rpires.v1.jms.topico");
		
		//Todos os pacakges
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
		
		
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection conexao = cf.createConnection();
		conexao.setClientID(TesteConsumidor2NTopicosObjeto.class.getName());

		conexao.start();
		
		Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topico = (Topic) context.lookup("MyTopic");
		//MessageConsumer consumer = session.createConsumer(topico);
		MessageConsumer consumer = session.createDurableSubscriber(topico, TesteConsumidor2NTopicosObjeto.class.getName());

		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				ObjectMessage msg = (ObjectMessage) message;
				try {
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
