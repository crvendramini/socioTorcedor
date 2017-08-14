package br.com.carlos.campanha.modelo;

import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;

public class Cliente {
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String nomeCompleto;
	private String eMail;
	private Calendar dataNascimento;
	private Times timeCoracao;
	
	public Cliente(Long id, String nomeCompleto, String eMail, Calendar dataNascimento, Times timeCoracao) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.eMail = eMail;
		this.dataNascimento = dataNascimento;
		this.timeCoracao = timeCoracao;
	}
	public Cliente() {
		
	}

	private List<Campanha> campanhas;

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Times getTimeCoracao() {
		return timeCoracao;
	}

	public void setTimeCoracao(Times timeCoracao) {
		this.timeCoracao = timeCoracao;
	}

	public List<Campanha> getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(List<Campanha> campanhas) {
		this.campanhas = campanhas;
	}


	public String toJson() {
		
		return new Gson().toJson(this);
	}
	public String toJson(List<Cliente> cliente) {
		return new Gson().toJson(cliente);
	}
	
}
