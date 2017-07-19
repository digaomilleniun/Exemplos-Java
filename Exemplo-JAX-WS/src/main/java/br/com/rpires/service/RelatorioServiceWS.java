/**
 * 
 */
package br.com.rpires.service;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author rpires
 *
 */
@WebService
public class RelatorioServiceWS {

	/**
	 * 
	 */
	public RelatorioServiceWS() {
		// TODO Auto-generated constructor stub
	}
	
	@Oneway
	@WebMethod(operationName="GerarRelatorio")
    public void gerarRelatorio() { 
        // código omitido
    }

}
