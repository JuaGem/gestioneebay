package it.prova.gestioneebay.service.acquisto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneebay.model.Acquisto;
import it.prova.gestioneebay.repository.acquisto.AcquistoRepository;

@Service
public class AcquistoServiceImpl implements AcquistoService {
	
	@Autowired
	private AcquistoRepository acquistoRepository;

	@Transactional(readOnly = true)
	public List<Acquisto> listAllAcquisti() {
		return (List<Acquisto>) acquistoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Acquisto caricaSingoloAcquisto(Long id) {
		return acquistoRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Acquisto acquistoInstance) {
		acquistoRepository.save(acquistoInstance);
	}

	@Transactional
	public void inserisciNuovo(Acquisto acquistoInstance) {
		acquistoRepository.save(acquistoInstance);
	}

	@Transactional
	public void rimuovi(Acquisto acquistoInstance) {
		acquistoRepository.delete(acquistoInstance);
	}

	@Transactional(readOnly = true)
	public Acquisto caricaSingoloAcquistoEager(Long id) {
		return acquistoRepository.findOneEager(id);
	}
	
	@Transactional(readOnly = true)
	public List<Acquisto> listAcquistiUtente(Long id) {
		return acquistoRepository.listAcquistiUtente(id);
	}

}
