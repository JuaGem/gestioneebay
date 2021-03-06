package it.prova.gestioneebay.repository.categoria;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.prova.gestioneebay.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
	
	@Modifying
    @Query(value="delete from categoria_annuncio  where annuncio_id= :id", nativeQuery = true)
    void deleteCategoriaByAnnuncio(@Param("id") Long id);
	
}
