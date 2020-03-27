package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.exceptions.TopicSaveException;
import com.example.modelo.TsscTopic;
import com.example.repositories.TsscTopicRepository;

public class TsscTopicServiceImp implements TsscTopicService{

	public TsscTopicRepository tsscTopicRespository;
	
	@Autowired
	public  TsscTopicServiceImp(TsscTopicRepository tsscTopicRepo) {	
		tsscTopicRespository= tsscTopicRepo;
	}
	@Override
	public TsscTopic createTopic(TsscTopic story) throws TopicSaveException {
		
		if(story.getDefaultGroups()<=0 || story.getDefaultSprints()<=0) {
			throw new TopicSaveException();
		}else {
			tsscTopicRespository.save(story);
		}
			
		return story;
	}

	@Override
	public TsscTopic getTsscTopic(long id) {		
		return tsscTopicRespository.findById(id).get();
	}

	@Override
	public void updateTsscTopic(TsscTopic topic) {
		// TODO Auto-generated method stub
		
	}

}
