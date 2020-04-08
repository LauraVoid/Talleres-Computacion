package co.edu.icesi.fi.tics.tssc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		TsscTopic n1= new TsscTopic();
		n1.setDefaultGroups(100);
		n1.setDefaultSprints(100);
		n1.setName("Primero");
		TsscTopic n2= new TsscTopic();
		n2.setDefaultGroups(100);
		n2.setDefaultSprints(100);
		n2.setName("Segundo");
		try {
			topicService.createTopic(n1);
			topicService.createTopic(n2);
		} catch (TopicSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
