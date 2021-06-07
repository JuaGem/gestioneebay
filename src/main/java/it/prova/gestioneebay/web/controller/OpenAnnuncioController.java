package it.prova.gestioneebay.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.prova.gestioneebay.model.Annuncio;
import it.prova.gestioneebay.service.annuncio.AnnuncioService;
import it.prova.gestioneebay.service.categoria.CategoriaService;

@Controller
@RequestMapping("/public")
public class OpenAnnuncioController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private AnnuncioService annuncioService;
	
	@GetMapping
	public String ricercaAnnuncio(ModelMap model) {
		model.addAttribute("list_categorie", categoriaService.listAllCategorie());
		return "public/ricercaAnnuncio";
	}
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST}) 
	public String listAnnunci(Annuncio example, ModelMap model) {
		List<Annuncio> annunci = annuncioService.findByExample(example);
		model.addAttribute("annunci_list_attribute", annunci);
		return "public/list";
	}
	
	@GetMapping("/dettaglio/{idAnnuncio}")
	public String showAnnuncio(@PathVariable(required = true) Long idAnnuncio, Model model) {
		model.addAttribute("dettaglio_articolo_attr", annuncioService.caricaSingoloAnnuncioEager(idAnnuncio));

		return "public/dettaglio";
	}
	
	@RequestMapping(value = "/listAll", method = {RequestMethod.GET, RequestMethod.POST}) 
	public String listAllAnnunci(ModelMap model) {
		
		model.addAttribute("annunci_list_attribute", annuncioService.listAllAnnunci());
		return "public/list";
	}

}
