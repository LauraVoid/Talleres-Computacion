package com.example.services;

import com.example.exceptions.GameSaveException;
import com.example.modelo.TsscGame;


public interface TsscGameService {
	
	public TsscGame createStory(TsscGame story)throws GameSaveException;
	public void updateTsscGame(TsscGame story);

}
