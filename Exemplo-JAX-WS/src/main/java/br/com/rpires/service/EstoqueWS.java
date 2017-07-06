/**
 * 
 */
package br.com.rpires.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

import br.com.rpires.dao.ItemDao;
import br.com.rpires.domain.Filtro;
import br.com.rpires.domain.Filtros;
import br.com.rpires.domain.Item;
import br.com.rpires.domain.ItemValidador;
import br.com.rpires.domain.ListaItens;
import br.com.rpires.domain.usuario.AutorizacaoException;
import br.com.rpires.domain.usuario.TokenDao;
import br.com.rpires.domain.usuario.TokenUsuario;

/**
 * @author rpires
 *
 */
@WebService
public class EstoqueWS {

	private ItemDao dao = new ItemDao();
	private TokenDao tokenDao = new TokenDao();

	@WebMethod(operationName="listarTodosOsItens")
	@WebResult(name="itens")
    public List<Item> getItens(@WebParam(name="filtros") Filtros filtros) {
        System.out.println("Chamando getItens()");
        List<Filtro> lista = filtros.getLista();
        List<Item> result = dao.todosItens(lista);
        return result;
    }
	
	/**
	 * 
	 * @ResponseWrapper Altera o nome do ns2:listarTodosOsItensResponse para ns2:itens
	 * 
	 * @param filtros
	 * @return
	 */
	@WebMethod(operationName="listarTodosOsItensWrapper")
	@ResponseWrapper(localName="itens")
	@WebResult(name="itens")
    public ListaItens getItensWrapper(@WebParam(name="filtros") Filtros filtros) {
        System.out.println("Chamando getItensWrapper()");
        List<Filtro> lista = filtros.getLista();
        List<Item> result = dao.todosItens(lista);
        return new ListaItens(result);
    }
	
	@WebMethod(operationName="cadastrarItem")
	@WebResult(name="item")
	public Item cadastrarItem(
			@WebParam(name="token", header=true) TokenUsuario token, 
			@WebParam(name="item") Item item) throws AutorizacaoException {
		
		System.out.println("cadastrando item " + item.toString() + ", Token: " + token);
		
		boolean isValido = tokenDao.ehValido(token);
		if (!isValido) {
			throw new AutorizacaoException("Autorização Falhou");
		}
		
		new ItemValidador(item).validate();
		
		dao.cadastrar(item);
		return item;
	}
}
