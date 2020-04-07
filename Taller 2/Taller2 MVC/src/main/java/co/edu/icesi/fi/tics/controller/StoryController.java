package co.edu.icesi.fi.tics.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.exceptions.GameNotEsxistException;
import co.edu.icesi.fi.tics.tssc.exceptions.StorySaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;
import co.edu.icesi.fi.tics.tssc.services.TsscStoryServiceImp;

@Controller
public class StoryController {
	
	public TsscStoryServiceImp storyService;
	
	@Autowired
	public StoryController(TsscStoryServiceImp story) {		
		storyService= story;
	}
	
	@GetMapping("/Story/index")
	public String indexStory(Model model) {
		model.addAttribute("stories", storyService.findAll());
		return "/Story/index";
	}
	
	@GetMapping("/Story/SaveStory")
	public String saveStory(Model model) {
		model.addAttribute("story", new TsscStory());
		return "Story/index";
	}
	@PostMapping("/Story/SaveStory")
	public String saveStory(@Valid TsscStory story, BindingResult binding,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if(binding.hasErrors()) {
			return "Story/indexStory";
			
		}
		if(!action.equals("Cancel")) {
			
			
			//EL ID DE GAME ESTA PENDIENTE
			long id= story.getTsscGame().getId();
			try {
				storyService.createStory(story, id);
			} catch (StorySaveException | GameNotEsxistException e) {
				
			}
			
			
			
		}
		return "Story/index";
	}

}
