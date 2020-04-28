package co.edu.icesi.fi.tics.tssc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import co.edu.icesi.fi.tics.tssc.exceptions.GameSaveException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicNoExistsException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicSaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;
import co.edu.icesi.fi.tics.tssc.services.TsscGameServiceImp;
import co.edu.icesi.fi.tics.tssc.services.TsscTopicServiceImp;

@Controller
public class GameController {
	
	public TsscGameServiceImp gameService;
	public TsscTopicServiceImp topicService;
	
	@Autowired
	public GameController(TsscGameServiceImp game, TsscTopicServiceImp topic) {
		gameService= game;
		topicService = topic;
	}
	
	@GetMapping("/game/")
	public String indexGame(Model model) {
		model.addAttribute("games", gameService.findAll());
		//PRUEBA
		
		
		return "game/indexGame";
	}
	
	@GetMapping("/game/save")
	public String saveGame(Model model, @ModelAttribute("tsscGame") TsscGame game) {
		//model.addAttribute("tsscGame", new TsscGame());
		model.addAttribute("topics", topicService.findAll());
		return "game/saveGame";
	}
	
	@PostMapping("/game/save")
	public String saveGame(@RequestParam(value = "action", required = true) String action,
			@Valid TsscGame tsscGame, BindingResult binding) {
		if(binding.hasErrors()) {
			return "game/saveGame";
			
		}
		if(!action.equals("Cancel")) {
		
			
			if(tsscGame.getTsscTopic()==null) {
				try {
					gameService.createGame(tsscGame);
				} catch (GameSaveException e) {
				}	
			}else {
				try {
					gameService.createGameTopic(tsscGame, tsscGame.getTsscTopic().getId());
				} catch (GameSaveException | TopicNoExistsException e) {
				}
			}
					
		}
		
		
		return "redirect:/game/";
	}
	
	@GetMapping("/game/edit/{id}")
	public String aditGame(@PathVariable("id") long id, Model model) {
		//model.addAttribute("tsscGame", new TsscGame());
		Optional<TsscGame>tsscGame= gameService.findById(id);
		if(tsscGame == null) {
			throw new IllegalArgumentException("No existe el Game id "+id);
		}else {
			model.addAttribute("tsscGame", tsscGame.get());
			model.addAttribute("topics", topicService.findAll());

		}
		return "game/edit-game";
	}
	
	@PostMapping("/game/edit/{id}")
	public String aditGame(@PathVariable("id") long id,@RequestParam(value = "action", required = true) String action
			,@Valid TsscGame tsscGame, BindingResult binding,Model model) {
		if(binding.hasErrors()) {
			model.addAttribute("topics", topicService.findAll());
			return "game/edit-game";
		}else {
			
			if(!action.equals("Cancel")) {
				try {
					gameService.createGame(tsscGame);
					
				} catch (GameSaveException e) {
					e.printStackTrace();
				}
			}
			return "redirect:/game/";
		}
		
	}
	@GetMapping("/game/del/{id}")
	public String delete(@PathVariable("id") long id) {
		
		TsscGame game = gameService.findById(id).get();
		gameService.delete(game);	
		return "redirect:/game/";
	}

}
