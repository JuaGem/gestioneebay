package it.prova.gestioneebay.repository.utente;

import java.util.List;
import java.util.Optional;

import it.prova.gestioneebay.model.StatoUtente;
import it.prova.gestioneebay.model.Utente;

public interface CustomUtenteRepository {
	List<Utente> findByExample(Utente example);

	public Long countByAdmin();

	public Optional<Utente> findOneEager(Long id);
	
	public List<StatoUtente> listAllStati();

}
