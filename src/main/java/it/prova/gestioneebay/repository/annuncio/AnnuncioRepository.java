package it.prova.gestioneebay.repository.annuncio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestioneebay.model.Annuncio;

public interface AnnuncioRepository extends CrudRepository<Annuncio, Long>, CustomAnnuncioRepository {
	
	@Query("from Annuncio a left join fetch a.categorie c where a.id = ?1")
	public Annuncio findOneEager(Long id);
	
	@Query("select a from Annuncio a join fetch a.utente u where u.id = ?1")
	public List<Annuncio> listAnnunciUtente(Long id);
	
}
