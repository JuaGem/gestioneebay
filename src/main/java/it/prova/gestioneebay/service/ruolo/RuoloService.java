package it.prova.gestioneebay.service.ruolo;

import java.util.List;

import it.prova.gestioneebay.model.Ruolo;

public interface RuoloService {
	public List<Ruolo> listAll();

	public Ruolo caricaSingoloElemento(Long id);

	public void aggiorna(Ruolo ruoloInstance);

	public void inserisciNuovo(Ruolo ruoloInstance);

	public void rimuovi(Ruolo ruoloInstance);

	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice);

	public List<Ruolo> findByArrayIdParam(String[] ids);

}
