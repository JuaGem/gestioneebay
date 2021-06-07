package it.prova.gestioneebay.repository.acquisto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestioneebay.model.Acquisto;

public interface AcquistoRepository extends CrudRepository<Acquisto, Long> {
	
	@Query("from Acquisto a left join fetch a.utente u where a.id = ?1")
	public Acquisto findOneEager(Long id);
	
	@Query("select a from Acquisto a join fetch a.utente u where u.id = ?1")
	public List<Acquisto> listAcquistiUtente(Long id);

}
