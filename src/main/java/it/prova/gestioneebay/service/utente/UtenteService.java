package it.prova.gestioneebay.service.utente;

import java.util.List;

import it.prova.gestioneebay.model.StatoUtente;
import it.prova.gestioneebay.model.Utente;

public interface UtenteService {

	public List<Utente> listAllUtenti();

	public Utente caricaSingoloUtente(Long id);

	public void aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Utente utenteInstance);

	public List<Utente> findByExample(Utente example);

	public Utente findByUsernameAndPassword(String username, String password);

	public Utente eseguiAccesso(String username, String password);

	public void invertUserAbilitation(Long utenteInstanceId);

	public Utente findByUsername(String username);

	public Utente findOneEager(Long id);

	public void cambiaStato(long id);

	public boolean isTheLastAdministrator(long id);
	
	public List<StatoUtente> listAllStati();

}
