package br.com.carlos.campanha.test;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

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

import com.google.gson.Gson;

import br.com.carlos.campanha.modelo.Campanha;
import br.com.carlos.campanha.modelo.Times;
import junit.framework.Assert;

public class testesCampanhas {
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
	public void testaQueBuscaUmaCampanhaCorretamente() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8181");
		String cont = target.path("/campanha/1").request().get(String.class);
		Campanha campanha = (Campanha) new Gson().fromJson(cont, Campanha.class);
		
		Assert.assertEquals("Atletico MG", campanha.getTime().getLabel());
	}
	
	
	@Test
	public void testaAdicionarCampanhas() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8181");
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		
		dataInicio.set(2016, 01, 01);
		dataFim.set(2017, 01, 01);
		
		
		Campanha campanha = new Campanha(null, "Campanha Fanaticos Corinthians", Times.Corinthians,
										  dataInicio, dataFim );
		
		
		String json = campanha.toJson();
		
		Entity<String> entity = Entity.entity(json, MediaType.APPLICATION_JSON);
		
		Response response = target.path("/campanha").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("Location");
		
		String conteudo = client.target(location).request().get(String.class);
		
		Assert.assertTrue(conteudo.contains("Campanha Fanaticos Corinthians"));
		
		
	}
	
	@Test
	public void testaObterListaDeCampanhasValidas() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8181");
		String cont = target.path("/campanha").request().get(String.class);
				
		Assert.assertTrue(cont.contains("teste"));
		
		
	}
	

}
