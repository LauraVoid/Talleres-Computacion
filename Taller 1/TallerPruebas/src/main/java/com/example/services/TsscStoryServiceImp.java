package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.modelo.TsscStory;
import com.example.repositories.TsscStoryRepository;

public class TsscStoryServiceImp implements TsscStoryService{

	
	
	private TsscStoryRepository storyRepo;
	
	
	public TsscStoryServiceImp(TsscStoryRepository story) {
		storyRepo=story;
		
	}

	@Override
	public TsscStory createStory(TsscStory story) {
		TsscStory newStory = storyRepo.save(story);
		return newStory;
	}

	@Override
	public TsscStory getTsscStory(long id) {
		Optional<TsscStory> found= storyRepo.findById(id);		
		return found.get();
	}

	@Override
	public void deleteTsscStory(TsscStory story) {		
		storyRepo.delete(story);
		
	}
	

}
