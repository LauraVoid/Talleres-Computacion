package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.exceptions.TopicNoExistsException;
import com.example.exceptions.TopicSaveException;
import com.example.modelo.TsscTopic;
import com.example.repositories.TsscTopicRepository;

public class TsscTopicServiceImp implements TsscTopicService {

	public TsscTopicRepository tsscTopicRespository;

	@Autowired
	public TsscTopicServiceImp(TsscTopicRepository tsscTopicRepo) {
		tsscTopicRespository = tsscTopicRepo;
	}

	@Override
	public TsscTopic createTopic(TsscTopic story) throws TopicSaveException {

		if (story == null) {
			throw new TopicSaveException();
		} else {
			if (story.getDefaultGroups() <= 0 || story.getDefaultSprints() <= 0) {
				throw new TopicSaveException();
			} else {
				tsscTopicRespository.save(story);
			}
		}

		return story;
	}

	@Override
	public TsscTopic getTsscTopic(long id) {
		return tsscTopicRespository.findById(id).get();
	}

	@Override
	public TsscTopic updateTsscTopic(TsscTopic topic) throws TopicNoExistsException, TopicSaveException {

		if (topic == null) {
			throw new TopicSaveException();
		} else {
			if (tsscTopicRespository.findById(topic.getId()).isPresent() == false) {
				throw new TopicNoExistsException();
			} else {
				if (topic.getDefaultGroups() <= 0 || topic.getDefaultSprints() <= 0) {
					throw new TopicSaveException();
				}

				return tsscTopicRespository.save(topic);

			}
		}

	}

}
