package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.exceptions.GameNotEsxistException;
import com.example.exceptions.GameSaveException;
import com.example.exceptions.TopicNoExistsException;
import com.example.modelo.TsscGame;
import com.example.modelo.TsscTopic;
import com.example.repositories.TsscGameRepository;
import com.example.repositories.TsscTopicRepository;

public class TsscGameServiceImp implements TsscGameService {

	public TsscGameRepository tsscGameRepository;
	public TsscTopicRepository tsscTopicRepository;

	@Autowired
	public TsscGameServiceImp(TsscGameRepository game, TsscTopicRepository topic) {
		tsscGameRepository = game;
		tsscTopicRepository = topic;

	}

	@Override
	public TsscGame createGame(TsscGame story, long topic) throws GameSaveException, TopicNoExistsException {

		if (story == null) {
			throw new GameSaveException();
		} else {
			if (story.getNGroups() <= 0 || story.getNSprints() <= 0) {
				throw new GameSaveException();
			} else {
				if (tsscTopicRepository.findById(topic).isPresent() == false) {
					throw new TopicNoExistsException();
				} else {
					tsscGameRepository.save(story);
				}
			}

			return story;
		}
	}

	@Override
	public TsscGame updateTsscGame(TsscGame story) throws GameSaveException, GameNotEsxistException {
		Optional<TsscGame> found = tsscGameRepository.findById(story.getId());

		if (found == null) {
			throw new GameNotEsxistException();
		} else {
			if (story.getNGroups() <= 0 || story.getNSprints() <= 0) {
				throw new GameSaveException();
			}

			return tsscGameRepository.save(found.get());
		}
	}

}
