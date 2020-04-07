package co.edu.icesi.fi.tics.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("topics");
		return "Topic/indexTopic";
		
	}
	@GetMapping("/Topic/saveTopic")
	public String saveTopic(Model model) {
		model.addAttribute("topic", new TsscTopic());
		return "topic/saved";		
	}
	
	@PostMapping("/topic/saveTopic")
	public String saveTopic(@Valid TsscTopic topic, BindingResult binding,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if(binding.hasErrors()) {
			return "Topic/indexTopic";
		}
		if(!action.equals("Cancel")) {
			
			model.addAttribute("id",topic.getId());
			model.addAttribute("description", topic.getDescription());
			model.addAttribute("name",topic.getName());
			model.addAttribute("defaultSprints", topic.getDefaultSprints());
			model.addAttribute("defaultGroups", topic.getDefaultGroups());
			model.addAttribute("groupPrefix",topic.getGroupPrefix());
		}
		
		return "/Topic/indexTopic";
	}
}
