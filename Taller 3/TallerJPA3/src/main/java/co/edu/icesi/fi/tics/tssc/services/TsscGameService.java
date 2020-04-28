package co.edu.icesi.fi.tics.tssc.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.exceptions.GameNotEsxistException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameSaveException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicNoExistsException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;

@Service
public interface TsscGameService {
	
	public TsscGame createGameTopic(TsscGame story,long topic)throws GameSaveException, TopicNoExistsException;
	public TsscGame createGame(TsscGame story)throws GameSaveException;
	public TsscGame createGame2(TsscTopic story)throws GameSaveException, TopicNoExistsException;
	public TsscGame updateTsscGame(TsscGame story)throws GameSaveException, GameNotEsxistException;
	public Iterable<TsscGame> findAll();
	public Optional<TsscGame>findById(long id);
	public void delete(TsscGame game);

}
