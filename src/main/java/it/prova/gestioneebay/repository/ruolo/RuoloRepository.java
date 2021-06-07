package it.prova.gestioneebay.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestioneebay.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
