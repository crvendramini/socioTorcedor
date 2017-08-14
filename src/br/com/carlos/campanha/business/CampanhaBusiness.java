package br.com.carlos.campanha.business;

import java.util.Calendar;
import java.util.List;

import br.com.carlos.campanha.dao.CampanhaDAO;
import br.com.carlos.campanha.modelo.Campanha;

public class CampanhaBusiness {

	static CampanhaDAO campanhaDAO = new CampanhaDAO();
	public static void adiciona(Campanha campanha) {
		
		atualizaCampanhasComDadasCoincidentes(campanha.getDataFim());
		
		
		campanhaDAO.adiciona(campanha);
		
	}

	private static void atualizaCampanhasComDadasCoincidentes(Calendar dataFinal) {
		List<Campanha> campanhas = new CampanhaDAO().getListCampanhaValida(dataFinal);
		
		int dias = 1;
		for (Campanha campanha : campanhas) {
			campanha.getDataFim().add(Calendar.DATE,dias++);
			campanhaDAO.atualiza(campanha);
		}
		
		
		
		
	}
	
	

}
