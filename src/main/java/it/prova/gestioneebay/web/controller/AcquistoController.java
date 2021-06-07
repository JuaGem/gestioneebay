package it.prova.gestioneebay.web.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestioneebay.model.Acquisto;
import it.prova.gestioneebay.model.Annuncio;
import it.prova.gestioneebay.model.Utente;
import it.prova.gestioneebay.service.acquisto.AcquistoService;
import it.prova.gestioneebay.service.annuncio.AnnuncioService;
import it.prova.gestioneebay.service.utente.UtenteService;

@Controller
@RequestMapping("/acquisti")
public class AcquistoController {
	
	@Autowired
	private AnnuncioService annuncioService;
	
	@Autowired
	private AcquistoService acquistoService;
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/compra/{idAnnuncio}")
	public String showAcquisto(@PathVariable(required = true) Long idAnnuncio, Model model) {
		model.addAttribute("dettaglio_articolo_attr", annuncioService.caricaSingoloAnnuncioEager(idAnnuncio));

		return "acquisti/compra";
	}
	
	@PostMapping("/listAcquisti")
	public String buyAcquisto(Long idAcquistoForBuy, Model model, RedirectAttributes redirectAttrs, Principal principal) {
		Annuncio annuncioInstance = annuncioService.caricaSingoloAnnuncioEager(idAcquistoForBuy);
		Utente utenteInSession = utenteService.findByUsername(principal.getName());
		
		Double creditoResiduo = utenteInSession.getCreditoResiduo() - annuncioInstance.getPrezzo();
		
		if(creditoResiduo < 0.0) {
			redirectAttrs.addFlashAttribute("errorMessage", "Attenzione! Credito insufficiente per acquistare il prodotto");
			return "redirect:/public";
		}
		
		utenteInSession.setCreditoResiduo(creditoResiduo);
		
		Acquisto acquisto = new Acquisto(annuncioInstance.getTestoAnnuncio(), annuncioInstance.getPrezzo());
		acquisto.setDateAcquisto(new Date());
		acquisto.setUtente(utenteInSession);
		
		acquistoService.inserisciNuovo(acquisto);
		annuncioInstance.setStatoAnnuncio(false);
		annuncioService.aggiorna(annuncioInstance);
		model.addAttribute("list_acquisti_attr", acquistoService.listAcquistiUtente(utenteInSession.getId()));
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "acquisti/listAcquisti";
	}
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST}) 
	public String list(Model model,Principal principal) {
		Utente utenteInSession = utenteService.findByUsername(principal.getName());
		model.addAttribute("list_acquisti_attr", acquistoService.listAcquistiUtente(utenteInSession.getId()));
		return "acquisti/listAcquisti";
	}
	
	@GetMapping("/dettaglio/{idAcquisto}")
	public String dettaglioAcquisto(@PathVariable(required = true) Long idAcquisto, Model model) {
		model.addAttribute("dettaglio_acquisto_attr", acquistoService.caricaSingoloAcquisto(idAcquisto));
		
		return "acquisti/dettaglio";
	}

}
