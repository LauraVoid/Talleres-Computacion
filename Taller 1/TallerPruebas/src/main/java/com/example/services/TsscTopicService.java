package com.example.services;

import com.example.exceptions.TopicNoExistsException;
import com.example.exceptions.TopicSaveException;
import com.example.modelo.TsscStory;
import com.example.modelo.TsscTopic;

public interface TsscTopicService {
	
	public TsscTopic createTopic(TsscTopic story) throws TopicSaveException;
	public TsscTopic getTsscTopic(long id);
	public TsscTopic updateTsscTopic(TsscTopic topic)throws TopicNoExistsException, TopicSaveException;


}
