package co.edu.icesi.fi.tics.tssc.controller;

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
	
	@GetMapping("/game/")
	public String indexGame(Model model) {
		model.addAttribute("games", gameService.findAll());
		return "game/indexGame";
	}
	
	@GetMapping("/game/save")
	public String saveGame(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		return "/game/saveGame";
	}
	
	@PostMapping("/game/save")
	public String saveGame(@RequestParam(value = "action", required = true) String action,
			@Valid TsscGame tsscGame, BindingResult binding) {
		if(binding.hasErrors()) {
			return "game/saveGame";
			
		}
		if(!action.equals("Cancel")) {
		
			try {
				gameService.createGame(tsscGame);
			} catch (GameSaveException e) {
			}
			return "redirect:/Games/";
		}
		
		
		return "Game/indexGame";
	}

}
