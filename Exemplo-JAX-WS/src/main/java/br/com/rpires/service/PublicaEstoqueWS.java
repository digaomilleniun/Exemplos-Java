/**
 * 
 */
package br.com.rpires.service;

import javax.xml.ws.Endpoint;

/**
 * @author rpires
 *
 */
public class PublicaEstoqueWS {

	public static void main(String[] args) {

		EstoqueWS implementacaoWS = new EstoqueWS();
		String URL = "http://localhost:8080/estoquews";

		System.out.println("EstoqueWS rodando: " + URL);

		// associando URL com implementacao
		Endpoint.publish(URL, implementacaoWS);
	}
}
