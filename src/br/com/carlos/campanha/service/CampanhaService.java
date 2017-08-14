package br.com.carlos.campanha.service;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.carlos.campanha.business.CampanhaBusiness;
import br.com.carlos.campanha.dao.CampanhaDAO;
import br.com.carlos.campanha.modelo.Campanha;

@Path("/campanha")
public class CampanhaService {
	
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON )
	@Consumes(MediaType.APPLICATION_JSON )
	public String busca(@PathParam("id") long id) {
		Campanha campanha = new CampanhaDAO().busca(id);
		return campanha.toJson();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON )
	public String listaCampanhasValidas() {
		Campanha campanha = new Campanha();
		List<Campanha> listCampanha = new CampanhaDAO().getListCampanhaValida();
		return campanha.toJson(listCampanha);
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON )
	public Response adiciona(String conteudo) {
		Campanha campanha = (Campanha) new Gson().fromJson(conteudo,Campanha.class);
		CampanhaBusiness.adiciona(campanha);
		
		URI uri = URI.create("/campanha/" + campanha.getId());
		return Response.created(uri).build();
	}
	
	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON )
	@Consumes(MediaType.APPLICATION_JSON )
	public Response remove(@PathParam("id") long id) {
		new CampanhaDAO().remove(id);
		return Response.ok().build();
		
	}

}
