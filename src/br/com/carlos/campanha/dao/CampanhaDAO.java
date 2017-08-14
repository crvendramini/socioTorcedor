package br.com.carlos.campanha.dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import br.com.carlos.campanha.modelo.Campanha;
import br.com.carlos.campanha.modelo.Times;

public class CampanhaDAO {

	private static Map<Long, Campanha> DbCampanha = new HashMap<Long, Campanha>();
	private static AtomicLong geradorId = new AtomicLong(1);
	
	static {
		Calendar data = Calendar.getInstance();
		data.add(Calendar.DATE, 1);
		Campanha campanha = new Campanha(1l, "teste", Times.Atletico_MG, Calendar.getInstance(),data);
		DbCampanha.put(1l, campanha);
	}
	
	
	public void adiciona(Campanha campanha) {
		campanha.getDataFim().set(Calendar.HOUR, 0);
		campanha.getDataFim().set(Calendar.MINUTE, 0);
		campanha.getDataFim().set(Calendar.SECOND, 0);
		long id = geradorId.incrementAndGet();
		campanha.setId(id);
		DbCampanha.put(id, campanha);
	}
	
	public void atualiza(Campanha campanha) {
		DbCampanha.put(campanha.getId(), campanha);
	}
	
	public Campanha busca(Long id) {
		return DbCampanha.get(id);
	}
	
	public List<Campanha> getListCampanhaValida(){
		return DbCampanha.entrySet().stream().filter(e -> e.getValue().
	              getDataInicio().before(Calendar.getInstance()) &&  e.getValue().
	              getDataFim().after(Calendar.getInstance())).map(map -> map.getValue()).collect(Collectors.toList());
                
	}

	public List<Campanha> getListCampanhaValida(Calendar dataFinal){
		dataFinal.set(Calendar.HOUR, 0);
		dataFinal.set(Calendar.MINUTE, 0);
		dataFinal.set(Calendar.SECOND, 0);
		return DbCampanha.entrySet().stream().filter(e -> e.getValue().
	              getDataFim().equals(dataFinal)).map(map -> map.getValue()).collect(Collectors.toList());
                
	}

	
	public Campanha remove(long id) {
		return DbCampanha.remove(id);
	}
	
	
}
