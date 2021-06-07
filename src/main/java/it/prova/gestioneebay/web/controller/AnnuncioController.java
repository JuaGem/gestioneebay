package it.prova.gestioneebay.web.controller;

import java.security.Principal;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestioneebay.model.Annuncio;
import it.prova.gestioneebay.model.InsertAnnuncioParam;
import it.prova.gestioneebay.service.annuncio.AnnuncioService;
import it.prova.gestioneebay.service.categoria.CategoriaService;
import it.prova.gestioneebay.service.utente.UtenteService;

@Controller
@RequestMapping("/annuncio")
public class AnnuncioController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private AnnuncioService annuncioService;
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/insert")
	public String createAnnuncio(Model model) {		
		model.addAttribute("insert_annuncio_attr", new Annuncio());
		model.addAttribute("list_categorie", categoriaService.listAllCategorie());
		return "annuncio/insert";
	}
	
	@PostMapping("/save")
	public String saveAnnuncio(@Validated(InsertAnnuncioParam.class) @ModelAttribute("insert_annuncio_attr") Annuncio annuncioInstance,
			BindingResult result, RedirectAttributes redirectAttrs, Model model,  Principal principal) {
		
		annuncioInstance.setDataPubblicazione(new Date());
		annuncioInstance.setStatoAnnuncio(true);
		annuncioInstance.setUtente(utenteService.findByUsername(principal.getName()));
		
		System.out.println(annuncioInstance);
		
		if (result.hasErrors()) {
			
			model.addAttribute("list_categorie", categoriaService.listAllCategorie());
			return "annuncio/insert";
 		}
		
		annuncioService.inserisciNuovo(annuncioInstance);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/public/list";
	}

}
