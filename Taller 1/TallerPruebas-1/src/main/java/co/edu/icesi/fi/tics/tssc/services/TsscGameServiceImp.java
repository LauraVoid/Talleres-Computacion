package co.edu.icesi.fi.tics.tssc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.exceptions.GameNotEsxistException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameSaveException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicNoExistsException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.repositories.TsscGameRepository;
import co.edu.icesi.fi.tics.tssc.repositories.TsscTopicRepository;

@Service
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
