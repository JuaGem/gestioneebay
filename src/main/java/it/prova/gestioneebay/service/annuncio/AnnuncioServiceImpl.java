package it.prova.gestioneebay.service.annuncio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneebay.model.Annuncio;
import it.prova.gestioneebay.model.Categoria;
import it.prova.gestioneebay.repository.annuncio.AnnuncioRepository;
import it.prova.gestioneebay.repository.categoria.CategoriaRepository;

@Service
public class AnnuncioServiceImpl implements AnnuncioService {

	@Autowired
	private AnnuncioRepository annuncioRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional(readOnly = true)
	public List<Annuncio> listAllAnnunci() {
		return (List<Annuncio>) annuncioRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Annuncio caricaSingoloAnnuncio(Long id) {
		return annuncioRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Annuncio annuncioInstance) {
		categoriaRepository.deleteCategoriaByAnnuncio(annuncioInstance.getId());

		annuncioRepository.save(annuncioInstance);

		if (annuncioInstance.getCategorie() != null) {
			for (Categoria categoriaItem : annuncioInstance.getCategorie()) {
				categoriaItem = categoriaRepository.findById(categoriaItem.getId()).get();
				categoriaItem.getAnnunci().add(annuncioInstance);
				categoriaRepository.save(categoriaItem);
			}
		}
	}

	@Transactional
	public void inserisciNuovo(Annuncio annuncioInstance) {
		annuncioRepository.save(annuncioInstance);

		if (annuncioInstance.getCategorie() != null) {

			for (Categoria categoriaItem : annuncioInstance.getCategorie()) {

				categoriaItem = categoriaRepository.findById(categoriaItem.getId()).get();
				categoriaItem.getAnnunci().add(annuncioInstance);
				categoriaRepository.save(categoriaItem);
			}
		}

	}

	@Transactional
	public void rimuovi(Annuncio annuncioInstance) {
		categoriaRepository.deleteCategoriaByAnnuncio(annuncioInstance.getId());
		annuncioRepository.delete(annuncioInstance);
	}

	@Transactional(readOnly = true)
	public List<Annuncio> findByExample(Annuncio example) {
		return annuncioRepository.findByExample(example);
	}

	@Transactional(readOnly = true)
	public Annuncio caricaSingoloAnnuncioEager(Long id) {
		return annuncioRepository.findOneEager(id);
	}

	@Transactional(readOnly = true)
	public List<Annuncio> listAnnunciUtente(Long id) {
		return annuncioRepository.listAnnunciUtente(id);
	}

}
