package it.prova.gestioneebay.service.utente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneebay.model.Ruolo;
import it.prova.gestioneebay.model.StatoUtente;
import it.prova.gestioneebay.model.Utente;
import it.prova.gestioneebay.repository.ruolo.RuoloRepository;
import it.prova.gestioneebay.repository.utente.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository repository;
	@Autowired
	private RuoloRepository ruoloRepository;

	@Transactional(readOnly = true)
	public List<Utente> listAllUtenti() {
		return (List<Utente>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Utente caricaSingoloUtente(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		repository.save(utenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		repository.save(utenteInstance);
	}

	@Transactional
	public void rimuovi(Utente utenteInstance) {
		repository.delete(utenteInstance);
	}

	@Transactional(readOnly = true)
	public List<Utente> findByExample(Utente example) {
		return repository.findByExample(example);
	}

	@Transactional(readOnly = true)
	public Utente eseguiAccesso(String username, String password) {
		return repository.findByUsernameAndPasswordAndStato(username, password, StatoUtente.ATTIVO);
	}

	@Override
	public Utente findByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}

	@Transactional
	public void invertUserAbilitation(Long utenteInstanceId) {
		Utente utenteInstance = caricaSingoloUtente(utenteInstanceId);
		if (utenteInstance == null)
			throw new RuntimeException("Elemento non trovato.");

		if (utenteInstance.getStato().equals(StatoUtente.ATTIVO))
			utenteInstance.setStato(StatoUtente.DISABILITATO);
		else if (utenteInstance.getStato().equals(StatoUtente.DISABILITATO))
			utenteInstance.setStato(StatoUtente.ATTIVO);
	}

	@Override
	@Transactional
	public Utente findByUsername(String username) {
		return repository.findByUsername(username).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public Utente findOneEager(Long id) {
		return repository.findOneEager(id).orElse(null);
	}

	@Transactional
	@Override
	public void cambiaStato(long id) {

		Utente utenteInstance = repository.findOneEager(id).get();


		if (utenteInstance.getRuoli().contains(ruoloRepository.findById(1L).get())) {
			if (utenteInstance.getStato().name().equals(StatoUtente.ATTIVO.name())) {
				if (repository.countByAdmin() <= 1) {
					System.out.println(repository.countByAdmin());
					throw new RuntimeException("errore, impossibile disattivare l'ultimo admin rimasto");
				}
				utenteInstance.setStato(StatoUtente.DISABILITATO);
			} else {
				utenteInstance.setStato(StatoUtente.ATTIVO);
			}

		} else {
			if (utenteInstance.getStato().name().equals(StatoUtente.ATTIVO.name())) {
				utenteInstance.setStato(StatoUtente.DISABILITATO);
			} else {
				utenteInstance.setStato(StatoUtente.ATTIVO);
			}
		}
		repository.save(utenteInstance);
	}
	@Override
	@Transactional
	public boolean isTheLastAdministrator(long id) {

		Utente utenteInstance = repository.findOneEager(id).get();
		long idRuoloAdministrator = 1L;
		Ruolo ruoloAdministrator = ruoloRepository.findById(idRuoloAdministrator).get();
		boolean utenteIsAdministrator = utenteInstance.getRuoli().contains(ruoloAdministrator);
		boolean isTheLast = repository.countByAdmin() <= 1;

		if (utenteIsAdministrator && isTheLast) {
			return true;
		}
		return false;
	}

	@Override
	public List<StatoUtente> listAllStati() {
		return repository.listAllStati();
	}
}
