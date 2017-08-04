package br.com.rpires.v1.jms.topico;

import java.io.StringWriter;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.xml.bind.JAXB;

import br.com.rpires.v1.jms.modelo.Pedido;
import br.com.rpires.v1.jms.modelo.PedidoFactory;

/**
 * 
 */

/**
 * @author rpires
 *
 * Envia 100 mensagens para o servidor de mensagens
 *
 */
public class TesteProdutorTopicoObjeto {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection conexao = cf.createConnection();

		conexao.start();
		
		Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination topico = (Destination) context.lookup("MyTopic");
		
		MessageProducer producer = session.createProducer(topico);
		PedidoFactory factory = new PedidoFactory();
		
		Pedido pedido = factory.geraPedidoComValores();
		
		for (int i = 0; i <= 1; i++) {
			Message msg = session.createObjectMessage(pedido);
			
			//Passando a propriedade para o selector analisar no consumidor.
			msg.setBooleanProperty("ebook", false);
			producer.send(msg);
		}

		new Scanner(System.in).nextLine();

		session.close();
		conexao.close();
		context.close();
	}

}
