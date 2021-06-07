package it.prova.gestioneebay.repository.annuncio;

import java.util.List;

import it.prova.gestioneebay.model.Annuncio;

public interface CustomAnnuncioRepository {
	
	public List<Annuncio> findByExample(Annuncio example);

}
