package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.exceptions.GameSaveException;
import com.example.modelo.TsscGame;
import com.example.repositories.TsscGameRepository;

public class TsscGameServiceImp implements TsscGameService{

	
	public TsscGameRepository tsscGameRepository;
	
	@Autowired
	 public TsscGameServiceImp(TsscGameRepository game) {
		 tsscGameRepository= game;
		
	}
	@Override
	public TsscGame createStory(TsscGame story) throws GameSaveException{
		TsscGame newGame = tsscGameRepository.save(story);
		return newGame;
	}
	
	@Override
	public void updateTsscGame(TsscGame story) {
		TsscGame found = tsscGameRepository.findById(story.getId()).get();
		tsscGameRepository.save(found);
		
	}
	
	

}
