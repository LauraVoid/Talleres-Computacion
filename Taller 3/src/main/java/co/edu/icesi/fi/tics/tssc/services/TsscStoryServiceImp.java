package co.edu.icesi.fi.tics.tssc.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.exceptions.GameNotEsxistException;
import co.edu.icesi.fi.tics.tssc.exceptions.StoryNotExistException;
import co.edu.icesi.fi.tics.tssc.exceptions.StorySaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;
import co.edu.icesi.fi.tics.tssc.repositories.TsscGameRepository;
import co.edu.icesi.fi.tics.tssc.repositories.TsscStoryRepository;

@Service
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

	@Override
	public Iterable<TsscStory> findAll() {
		return storyRepository.findAll();
	}

	@Override
	public Optional<TsscStory> findById(long id) {
		return storyRepository.findById(id);
	}

	@Override
	public void delete(TsscStory story) {
		storyRepository.delete(story);
		
	}

}
