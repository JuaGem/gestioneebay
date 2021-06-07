package it.prova.gestioneebay.service.acquisto;

import java.util.List;

import it.prova.gestioneebay.model.Acquisto;

public interface AcquistoService {
	
	public List<Acquisto> listAllAcquisti();

	public Acquisto caricaSingoloAcquisto(Long id);

	public void aggiorna(Acquisto acquistoInstance);

	public void inserisciNuovo(Acquisto acquistoInstance);

	public void rimuovi(Acquisto acquistoInstance);
	
	public Acquisto caricaSingoloAcquistoEager(Long id);
	
	public List<Acquisto> listAcquistiUtente(Long id);

}
