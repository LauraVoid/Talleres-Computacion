package co.edu.icesi.fi.tics.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.exceptions.GameSaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.services.TsscGameServiceImp;

@Controller
public class GameController {
	
	public TsscGameServiceImp gameService;
	
	@Autowired
	public GameController(TsscGameServiceImp game) {
		gameService= game;
	}
	
	@GetMapping("/Game/indexGame")
	public String indexGame(Model model) {
		model.addAttribute("games", gameService.findAll());
		return "/Game/indexGame";
	}
	
	@GetMapping("/Game/saveGame")
	public String saveGame(Model model) {
		model.addAttribute("game", new TsscGame());
		return "Game/indexGame";
	}
	
	@PostMapping("/Game/saveGame")
	public String saveGame(@Valid TsscGame game, BindingResult binding,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if(binding.hasErrors()) {
			return "Game/indexGame";
			
		}
		if(!action.equals("Cancel")) {
		
			try {
				gameService.createGame(game);
			} catch (GameSaveException e) {
			}
		}
		
		
		return "Game/indexGame";
	}

}
