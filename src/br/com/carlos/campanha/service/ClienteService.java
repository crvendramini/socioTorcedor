package br.com.carlos.campanha.service;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.carlos.campanha.dao.ClienteDAO;
import br.com.carlos.campanha.modelo.Campanha;
import br.com.carlos.campanha.modelo.Cliente;
import junit.framework.Assert;

@Path("/cliente")
public class ClienteService {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON )
	@Consumes(MediaType.APPLICATION_JSON )
	public String busca(@PathParam("id") long id) {
		Cliente cliente = new ClienteDAO().busca(id);
		return cliente.toJson();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON )
	public String listaCampanhasValidas() {
		Cliente cliente = new Cliente();
		List<Cliente> listCliente = new ClienteDAO().getListClientes();
		return cliente.toJson(listCliente);
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON )
	public Response adiciona(String conteudo) {
		Cliente cliente = (Cliente) new Gson().fromJson(conteudo,Cliente.class);
		new ClienteDAO().adiciona(cliente);
		
		URI uri = URI.create("/cliente/" + cliente.getId());
		return Response.created(uri).build();
	}
	
	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON )
	@Consumes(MediaType.APPLICATION_JSON )
	public Response remove(@PathParam("id") long id) {
		new ClienteDAO().remove(id);
		return Response.ok().build();
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response associarClienteACampanha(String conteudo) {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8181");
		String cont = target.path("/campanha").request().get(String.class);
				

		Campanha campanha = (Campanha) new Gson().fromJson(cont, Campanha.class);
		
		Cliente cliente = (Cliente) new Gson().fromJson(conteudo,Cliente.class);
		if (new ClienteDAO().associaClienteACampanha(cliente)==1) {
			return Response.notModified("Cliente já Cadastrado").build();
		}else {
			URI uri = URI.create("/cliente/" + cliente.getId());
			return Response.ok(uri).build();

		}		
	}
	
	
}
