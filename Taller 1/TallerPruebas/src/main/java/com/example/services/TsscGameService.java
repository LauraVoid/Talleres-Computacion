package com.example.services;

import com.example.exceptions.GameNotEsxistException;
import com.example.exceptions.GameSaveException;
import com.example.exceptions.TopicNoExistsException;
import com.example.modelo.TsscGame;
import com.example.modelo.TsscTopic;


public interface TsscGameService {
	
	public TsscGame createGame(TsscGame story,long topic)throws GameSaveException, TopicNoExistsException;
	public TsscGame updateTsscGame(TsscGame story)throws GameSaveException, GameNotEsxistException;

}
