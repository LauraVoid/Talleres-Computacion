package com.example.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.exceptions.GameNotEsxistException;
import com.example.exceptions.StoryNotExistException;
import com.example.exceptions.StorySaveException;
import com.example.modelo.TsscGame;
import com.example.modelo.TsscStory;
import com.example.repositories.TsscGameRepository;
import com.example.repositories.TsscStoryRepository;

public class TsscStoryServiceImp implements TsscStoryService {

	private TsscStoryRepository storyRepository;
	private TsscGameRepository gameRepository;

	@Autowired
	public TsscStoryServiceImp(TsscStoryRepository story, TsscGameRepository gameRepo) {
		storyRepository = story;
		gameRepository = gameRepo;

	}

	@Override
	public TsscStory createStory(TsscStory story, long game) throws StorySaveException, GameNotEsxistException {
		if (story == null) {
			throw new StorySaveException();
		} else {
			if (gameRepository.findById(game).isPresent() == false) {
				throw new GameNotEsxistException();
			} else {
				if (story.getBusinessValue().compareTo(BigDecimal.ZERO) <= 0
						|| story.getInitialSprint().compareTo(BigDecimal.ZERO) <= 0
						|| story.getPriority().compareTo(BigDecimal.ZERO) <= 0) {
					throw new StorySaveException();
				} else {
					storyRepository.save(story);
				}
				return story;
			}

		}

	}

	@Override
	public TsscStory updateStory(TsscStory story) throws StorySaveException, StoryNotExistException {
		if (story == null) {
			throw new StorySaveException();

		} else {

			if (storyRepository.findById(story.getId()).isPresent() == false) {
				throw new StoryNotExistException();
			} else {
				if (story.getBusinessValue().compareTo(BigDecimal.ZERO) <= 0
						|| story.getInitialSprint().compareTo(BigDecimal.ZERO) <= 0
						|| story.getPriority().compareTo(BigDecimal.ZERO) <= 0) {
					throw new StorySaveException();
				} else {
					storyRepository.save(story);
				}
			}
			return story;

		}

	}

}
