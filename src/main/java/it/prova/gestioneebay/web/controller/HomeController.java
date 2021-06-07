package it.prova.gestioneebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.gestioneebay.service.categoria.CategoriaService;

@Controller
public class HomeController {
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = { "/home", "" })
	public String loginMessage(Model model) {
		
		model.addAttribute("list_categorie", categoriaService.listAllCategorie());
		return "public/ricercaAnnuncio";
	}
}
