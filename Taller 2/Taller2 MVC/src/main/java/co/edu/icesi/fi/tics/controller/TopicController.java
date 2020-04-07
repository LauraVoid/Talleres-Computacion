package co.edu.icesi.fi.tics.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.exceptions.TopicSaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;
import co.edu.icesi.fi.tics.tssc.services.TsscTopicServiceImp;

@Controller
public class TopicController {

	public  TsscTopicServiceImp topicService;
	
	@Autowired
	public TopicController (TsscTopicServiceImp topic) {
		topicService=topic;
	}
	
	@GetMapping("/Topic/indexTopic")
	public String indexTopic(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "Topic/indexTopic";
		
	}
	@GetMapping("/Topic/SaveTopic")
	public String saveTopic(Model model) {
		model.addAttribute("topic", new TsscTopic());
		return "topic/saved";		
	}
	
	@PostMapping("/topic/SaveTopic")
	public String saveTopic(@Valid TsscTopic topic, BindingResult binding,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if(binding.hasErrors()) {
			return "Topic/indexTopic";
		}
		if(!action.equals("Cancel")) {
			
			
			try {
				topicService.createTopic(topic);
			} catch (TopicSaveException e) {
				
			}
		}
		
		return "/Topic/indexTopic";
	}
}
