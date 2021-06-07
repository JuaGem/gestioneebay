package it.prova.gestioneebay.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestioneebay.model.EditParam;
import it.prova.gestioneebay.model.Utente;
import it.prova.gestioneebay.service.ruolo.RuoloService;
import it.prova.gestioneebay.service.utente.UtenteService;

@Controller
@RequestMapping(value = "/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private RuoloService ruoloService;

	@GetMapping
	public ModelAndView listAllUtenti() {
		ModelAndView mv = new ModelAndView();
		List<Utente> utenti = utenteService.listAllUtenti();
		mv.addObject("utente_list_attribute", utenti);
		mv.setViewName("utente/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchUtente(Model model) {
		model.addAttribute("ruoli_list_attribute", ruoloService.listAll());
		model.addAttribute("stato_list_attribute", utenteService.listAllStati());
		return "utente/search";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String listUtenti(Utente utenteExample, ModelMap model) {
		List<Utente> utenti = utenteService.findByExample(utenteExample);
		model.addAttribute("utente_list_attribute", utenti);
		return "utente/list";
	}

	@PostMapping("/cambiaStato")
	public String cambiaStato(@RequestParam(name = "idUtenteForChangingStato", required = true) Long idUtente,RedirectAttributes redirectAttrs) {
		boolean isTheLast = utenteService.isTheLastAdministrator(idUtente);
		if (isTheLast) {
			redirectAttrs.addFlashAttribute("errorMessage", "è l'ultimo admin,non posso modificare il suo ruolo");
			return "redirect:/utente/list";
		}
		utenteService.cambiaStato(idUtente);
		return "redirect:/utente";
	}

	@GetMapping("/show/{idUtente}")
	public String showFilm(@PathVariable(name = "idUtente", required = true) Long idUtente, Model model) {
		model.addAttribute("show_utente_attr", utenteService.findOneEager(idUtente));
		return "utente/show";
	}

	@GetMapping("/edit/{idUtente}")
	public String editUtente(@PathVariable(required = true) Long idUtente, Model model) {
		model.addAttribute("edit_utente_attribute", utenteService.findOneEager(idUtente));
		model.addAttribute("ruoli_list_attribute", ruoloService.listAll());
		return "utente/edit";
	}

	@PostMapping("/edit/update")
	public String updateContribuente(@RequestParam(value = "error", required = false) String error, Model model,
			@Validated(EditParam.class) @ModelAttribute("edit_utente_attribute") Utente utente, BindingResult result,
			RedirectAttributes redirectAttrs) {
		boolean isTheLast = utenteService.isTheLastAdministrator(utente.getId());
		if (isTheLast) {
			model.addAttribute("errorMessage", "è l'ultimo admin,non posso modificare il suo ruolo");
		}
		if (result.hasErrors()) {
			model.addAttribute("ruoli_list_attribute", ruoloService.listAll());
			return "utente/edit";
		}
		Utente utentePreModifica = utenteService.caricaSingoloUtente(utente.getId());
		utente.setPassword(utentePreModifica.getPassword());
		utente.setStato(utentePreModifica.getStato());
		utente.setAcquisti(utentePreModifica.getAcquisti());
		utente.setDateCreated(utentePreModifica.getDateCreated());
		utenteService.aggiorna(utente);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente/list";
	}

}
