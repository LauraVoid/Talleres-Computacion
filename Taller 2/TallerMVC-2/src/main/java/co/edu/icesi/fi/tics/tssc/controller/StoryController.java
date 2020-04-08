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

import co.edu.icesi.fi.tics.tssc.exceptions.GameNotEsxistException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameSaveException;
import co.edu.icesi.fi.tics.tssc.exceptions.StorySaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;
import co.edu.icesi.fi.tics.tssc.services.TsscGameServiceImp;
import co.edu.icesi.fi.tics.tssc.services.TsscStoryServiceImp;

@Controller
public class StoryController {
	
	public TsscStoryServiceImp storyService;
	public TsscGameServiceImp gameService;
	
	@Autowired
	public StoryController(TsscStoryServiceImp story, TsscGameServiceImp game) {		
		storyService= story;
		gameService=game;
		//PRUEBA
		
		
	}
	
	@GetMapping("/story/")
	public String indexStory(Model model) {
		model.addAttribute("stories", storyService.findAll());
		TsscGame g1= new TsscGame();
		g1.setNGroups(10);
		g1.setNSprints(100);
		g1.setName("juego1");
		try {
			gameService.createGame(g1);
		} catch (GameSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Story/indexStory";
	}
	
	@GetMapping("/story/save")
	public String saveStory(Model model, @ModelAttribute("tsscStory") TsscStory story) {
		//model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("games", gameService.findAll());
		
		return "story/saveStory";
	}
	@PostMapping("/story/save")
	public String saveStory(@Valid TsscStory tsscStory, BindingResult binding,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if(binding.hasErrors()) {
			return "story/saveStory";
			
		}
		if(!action.equals("Cancel")) {
			
			
			//EL ID DE GAME ESTA PENDIENTE
			long id= tsscStory.getTsscGame().getId();
					//tsscStory.getTsscGame().getId();
			try {
				storyService.createStory(tsscStory, id);
			} catch (StorySaveException | GameNotEsxistException e) {
				System.out.println(e.getMessage());
			}
			return "redirect:/story/";
			
			
			
		}
		return "story/index";
	}

}
