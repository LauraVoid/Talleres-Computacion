package com.example.services;

import com.example.exceptions.GameNotEsxistException;
import com.example.exceptions.StoryNotExistException;
import com.example.exceptions.StorySaveException;
import com.example.modelo.TsscStory;

public interface TsscStoryService {
	
	public TsscStory createStory(TsscStory story, long game)throws StorySaveException, GameNotEsxistException;
	public TsscStory updateStory(TsscStory story)throws StorySaveException,StoryNotExistException;


}
