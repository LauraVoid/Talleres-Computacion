package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.exceptions.GameSaveException;
import com.example.exceptions.TopicNoExistsException;
import com.example.modelo.TsscGame;
import com.example.modelo.TsscTopic;
import com.example.repositories.TsscGameRepository;
import com.example.repositories.TsscTopicRepository;

public class TsscGameServiceImp implements TsscGameService{

	
	public TsscGameRepository tsscGameRepository;
	public TsscTopicRepository tsscTopicRepository;
	
	@Autowired
	 public TsscGameServiceImp(TsscGameRepository game, TsscTopicRepository topic) {
		 tsscGameRepository= game;
		 tsscTopicRepository= topic;
		
	}
	@Override
	public TsscGame createGame(TsscGame story,long topic) throws GameSaveException, TopicNoExistsException{
		
		if(story.getNGroups()<=0 || story.getNSprints()<=0) {
			throw new GameSaveException();
		}else {
			
			Optional<TsscTopic> found= tsscTopicRepository.findById(topic);
			if(found==null) {
				throw new TopicNoExistsException();
			}else {
				tsscGameRepository.save(story);
			}
		}
		
		return story;
	}
	
	@Override
	public void updateTsscGame(TsscGame story)throws GameSaveException {
		TsscGame found = tsscGameRepository.findById(story.getId()).get();
		tsscGameRepository.save(found);
		
	}
	
	

}
