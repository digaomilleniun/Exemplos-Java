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
		
		RelatorioServiceWS relWS = new RelatorioServiceWS();
		
		String URLRel = "http://localhost:8081/relatoriows";

		System.out.println("RelatorioServiceWS rodando: " + URLRel);

		// associando URL com implementacao
		Endpoint.publish(URLRel, relWS);
		
	}
}
