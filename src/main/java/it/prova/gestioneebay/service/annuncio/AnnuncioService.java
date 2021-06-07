package it.prova.gestioneebay.service.annuncio;

import java.util.List;

import it.prova.gestioneebay.model.Annuncio;

public interface AnnuncioService {
	
	public List<Annuncio> listAllAnnunci();

	public Annuncio caricaSingoloAnnuncio(Long id);

	public void aggiorna(Annuncio annuncioInstance);

	public void inserisciNuovo(Annuncio annuncioInstance);

	public void rimuovi(Annuncio annuncioInstance);

	public List<Annuncio> findByExample(Annuncio example);
	
	public Annuncio caricaSingoloAnnuncioEager(Long id);
	
	public List<Annuncio> listAnnunciUtente(Long id);

}
