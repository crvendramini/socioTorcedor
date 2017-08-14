package br.com.carlos.campanha.modelo;

public enum Times {
	VASCO(1),
	Botafogo(2),
	Flamengo(3),
	Fluminense(4), 
	Corinthians(5),
	Palmeiras(6),
	Ponte_Preta(7),
	Santos(8),
	São_Paulo(9), 
	Atletico_MG(10),
	Cruzeiro(11), 
	Bahia(12),
	Vitoria(13), 
	Atletico_PR(14),
	Coritiba(15), 
	Avaí(16),
	Chapecoense(17), 
	Atletico_GO(18), 
	Sport(19), 
	GrEmio(20); 

	
	private final int valor;
		
	private Times(int valor) {
		this.valor = valor;
	}
	
	public int getValor(){
		return valor;
	}
	
	public String getLabel(){
		String resultado = this.toString().replaceAll("_", " ");
		return resultado;
	}
}
