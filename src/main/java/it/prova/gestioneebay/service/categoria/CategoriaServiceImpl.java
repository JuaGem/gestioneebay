package it.prova.gestioneebay.service.categoria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneebay.model.Categoria;
import it.prova.gestioneebay.repository.categoria.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Categoria> listAllCategorie() {
		return (List<Categoria>)categoriaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Categoria caricaSingolaCategoria(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Categoria categoriaInstance) {
		categoriaRepository.save(categoriaInstance);
	}

	@Transactional
	public void inserisciNuovo(Categoria categoriaInstance) {
		categoriaRepository.save(categoriaInstance);
	}

	@Transactional
	public void rimuovi(Categoria categoriaInstance) {
//		categoriaInstance = entityManager.merge(categoriaInstance);
		
		if(!categoriaInstance.getAnnunci().isEmpty())
			throw new RuntimeException("Impossibile rimuovere la categoria, ha ancora delgi annunci associati");
		
		categoriaRepository.delete(categoriaInstance);
	}

	@Override
	public List<Categoria> findByExample(Categoria example) {
		// TODO Auto-generated method stub
		return null;
	}

}
