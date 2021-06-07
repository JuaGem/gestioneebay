package it.prova.gestioneebay.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestioneebay.model.Annuncio;
import it.prova.gestioneebay.model.EditAnnuncioParam;
import it.prova.gestioneebay.model.RegistrationParam;
import it.prova.gestioneebay.model.Utente;
import it.prova.gestioneebay.service.annuncio.AnnuncioService;
import it.prova.gestioneebay.service.categoria.CategoriaService;
import it.prova.gestioneebay.service.utente.UtenteService;

@Controller
@RequestMapping(value = "/areaprivata")
public class AreaPrivataController {
	
	@Autowired
	private AnnuncioService annuncioService;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String goToAreaPrivata(Principal principal, Model model) {
		Utente utenteInSession = utenteService.findByUsername(principal.getName());
		model.addAttribute("utente_registration_attribute", utenteInSession);
		return "areaprivata/index";
	}

	@GetMapping(value = "/show")
	public String details(Principal principal, Model model) {
		Utente utenteInSession = utenteService.findByUsername(principal.getName());
		model.addAttribute("utente_registration_attribute", utenteInSession);
		return "areaprivata/show";
	}

	@PostMapping(value = "/edit")
	public String edit(@RequestParam String confermaPassword,
			@Validated(RegistrationParam.class) @ModelAttribute("utente_registration_attribute") Utente utente,
			BindingResult result, RedirectAttributes redirectAttrs) {

		if (utente.getPassword() != null && !utente.getPassword().equals(confermaPassword)) {
			result.rejectValue("password", "confermapassword.errormessage", "Le due password non coincidono");
		}
		if (result.hasErrors()) {
			return "areaprivata/show";
		}
		Utente utentePreModifica = utenteService.caricaSingoloUtente(utente.getId());
		utente.setAcquisti(utentePreModifica.getAcquisti());
		utente.setCreditoResiduo(utentePreModifica.getCreditoResiduo());
		utente.setDateCreated(utentePreModifica.getDateCreated());
		utente.setStato(utentePreModifica.getStato());
		utente.setRuoli(utentePreModifica.getRuoli());
		utente.setPassword(passwordEncoder.encode(utente.getPassword()));
		
		utenteService.aggiorna(utente);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/areaprivata";
	}
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST}) 
	public String listAnnunci(Model model, Principal principal) {
		Utente utenteInSession = utenteService.findByUsername(principal.getName());
		
		model.addAttribute("annunci_list_attribute", annuncioService.listAnnunciUtente(utenteInSession.getId()));
		return "areaprivata/annunci";
	}
	
	@GetMapping("/dettaglio/{idAnnuncio}")
	public String showAnnuncio(@PathVariable(required = true) Long idAnnuncio, Model model) {
		model.addAttribute("dettaglio_articolo_attr", annuncioService.caricaSingoloAnnuncioEager(idAnnuncio));

		return "areaprivata/dettaglio";
	}
	
	@GetMapping("/editAnnuncio/{idAnnuncio}")
	public String editAnnuncio(@PathVariable(required = true) Long idAnnuncio, Model model) {
		model.addAttribute("edit_articolo_attr", annuncioService.caricaSingoloAnnuncioEager(idAnnuncio));
		model.addAttribute("list_categorie", categoriaService.listAllCategorie());

		return "areaprivata/editAnnuncio";
	}
	
	@PostMapping("/saveupdate")
	public String saveEditContribuente(@Validated(EditAnnuncioParam.class) @ModelAttribute("edit_articolo_attr") Annuncio annuncioInstance,
			BindingResult result, RedirectAttributes redirectAttrs, Model model, Principal principal) {
		Annuncio annuncioPreModifica = annuncioService.caricaSingoloAnnuncioEager(annuncioInstance.getId());
		
		annuncioInstance.setDataPubblicazione(annuncioPreModifica.getDataPubblicazione());
		annuncioInstance.setStatoAnnuncio(true);
		annuncioInstance.setUtente(annuncioPreModifica.getUtente());

		
		if (result.hasErrors()) {
			
			model.addAttribute("list_categorie", categoriaService.listAllCategorie());
			return "areaprivata/editAnnuncio";
		}

		annuncioService.aggiorna(annuncioInstance);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");

		return "redirect:/areaprivata/list";
	}
	
	@PostMapping("/archivia")
	public String archiviaAnnuncio(Long idAnnuncioForDelete) {
		Annuncio annuncioInstance = annuncioService.caricaSingoloAnnuncio(idAnnuncioForDelete);
		
		annuncioService.rimuovi(annuncioInstance);
		
		return "redirect:/areaprivata/list";
	}
	

}
