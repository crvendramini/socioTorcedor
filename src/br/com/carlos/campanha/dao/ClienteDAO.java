package br.com.carlos.campanha.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import br.com.carlos.campanha.modelo.Cliente;


public class ClienteDAO {

	private static Map<Long, Cliente> DbCliente = new HashMap<Long, Cliente>();
	private static AtomicLong geradorId = new AtomicLong(1);
	

	
	
	public void adiciona(Cliente Cliente) {
		long id = geradorId.incrementAndGet();
		Cliente.setId(id);
		DbCliente.put(id, Cliente);
	}
	
	public void atualiza(Cliente Cliente) {
		DbCliente.put(Cliente.getId(), Cliente);
	}
	
	public Cliente busca(Long id) {
		return DbCliente.get(id);
	}
	
	
	
	public Cliente remove(long id) {
		return DbCliente.remove(id);
	}

	public List<Cliente> getListClientes() {
		return DbCliente.entrySet().stream().map(map -> map.getValue()).collect(Collectors.toList());
	}

	public int associaClienteACampanha(Cliente cliente) {
		Cliente cli = DbCliente.get(cliente.getId());
		if (cli == null) {
			return 1;
		}
		atualiza(cli);
		return 0;
		
	}
	
	
}
