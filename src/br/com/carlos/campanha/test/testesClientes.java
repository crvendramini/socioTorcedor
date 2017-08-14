package br.com.carlos.campanha.test;

import java.net.URI;
import java.util.Calendar;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.carlos.campanha.modelo.Cliente;
import br.com.carlos.campanha.modelo.Times;
import junit.framework.Assert;

public class testesClientes {
	private HttpServer server;
	
	
	@Before
	public void iniciaServidor() {
        ResourceConfig config = new ResourceConfig().packages("br.com.carlos.campanha.service");
        URI uri = URI.create("http://localhost:8181/");
        server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
	}
	
	@After
	public void encerraServidor() {
		server.shutdown();
	}
	
	@Test
	public void TesteDeAssociacaoDeClientesComCampanhas() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8181");
		Calendar DataNascimento = Calendar.getInstance();
		DataNascimento.set(1970, 8, 17);
		Cliente cliente = new Cliente(null,"Carlos Roberto Vendramini","carlos_vendramini@yahoo.com",DataNascimento,Times.Corinthians);
		
		
		String json = cliente.toJson();
		
		Entity<String> entity = Entity.entity(json, MediaType.APPLICATION_JSON);
		
		Response response = target.path("/cliente").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("Location");
		
		String conteudo = client.target(location).request().get(String.class);
		
		Assert.assertTrue(conteudo.contains("Carlos Roberto Vendramini"));
		
		
	}
	
	
}
