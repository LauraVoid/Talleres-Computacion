package co.edu.icesi.fi.tics.tssc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.exceptions.TopicNoExistsException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicSaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;
import co.edu.icesi.fi.tics.tssc.services.TsscTopicServiceImp;

@Controller
public class TopicController {

	public TsscTopicServiceImp topicService;

	@Autowired
	public TopicController(TsscTopicServiceImp topic) {
		topicService = topic;
	}

	@GetMapping("/topics/")
	public String indexTopic(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "topics/index";

	}

	@GetMapping("/topics/add")
	public String saveTopic(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "topics/add-topic";
	}

	@PostMapping("/topics/add")
	public String saveTopic(@RequestParam(value = "action", required = true) String action, @Valid TsscTopic tsscTopic,
			BindingResult binding, Model model) {

		if (!action.equals("Cancel")) {

			if (binding.hasErrors()) {
				return "topics/add-topic";

			} else {
				
				try {
					topicService.createTopic(tsscTopic);
				} catch (TopicSaveException e) {

				}
				return "redirect:/topics/";
			}
			
		} else {

			return "topics/index";
		}
	}
	
	@GetMapping("/topics/edit/{id}")
	public String editTopic(@PathVariable("id")long id,Model model) {
		Optional<TsscTopic>tsscTopic = topicService.findById(id);
		if(tsscTopic== null) {
			throw new IllegalArgumentException("No existe el Topic id "+id);
		}
		model.addAttribute("tsscTopic", tsscTopic.get());
		return "topics/edit-topic";
	}
	
	@PostMapping("/topics/edit/{id}")
	public String editTopic(@PathVariable("id")long id,@RequestParam(value = "action", required = true) String action,
			@Valid TsscTopic tssctopic, BindingResult binding) {

		if(binding.hasErrors()) {
			return "topics/edit-topic";
		}
		if(!action.equals("Cancel")) {
			try {
				topicService.updateTsscTopic(tssctopic);
			} catch (TopicNoExistsException | TopicSaveException e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/topics/";
	}
	
	@GetMapping("/login")
	public String loginAdmin() {
		return "loginAdmin";
	}
	

}
