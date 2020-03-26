package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.modelo.TsscSprint;

import com.example.repositories.TsscSprintRepository;

public class TsscSprintServiceImp implements TsscSprintService {

	
	public TsscSprintRepository tsscSprintRepository;
	
	@Autowired
	public TsscSprintServiceImp(TsscSprintRepository tsscRepo) {
		tsscSprintRepository= tsscRepo;
	}
	@Override
	public TsscSprint createStory(TsscSprint story) {
		TsscSprint newSprint = tsscSprintRepository.save(story);
		
		return newSprint;
	}

	@Override
	public TsscSprint getTsscSprint(long id) {
		TsscSprint found = tsscSprintRepository.findById(id).get();
		return found;
	}

	@Override
	public void deleteTsscStory(TsscSprint story) {
		
		tsscSprintRepository.delete(story);
		
	}

	
	
	

}
