package co.edu.icesi.fi.tics.tssc.services;

import co.edu.icesi.fi.tics.tssc.exceptions.GameNotEsxistException;
import co.edu.icesi.fi.tics.tssc.exceptions.StoryNotExistException;
import co.edu.icesi.fi.tics.tssc.exceptions.StorySaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;

public interface TsscStoryService {
	
	public TsscStory createStory(TsscStory story, long game)throws StorySaveException, GameNotEsxistException;
	public TsscStory updateStory(TsscStory story)throws StorySaveException,StoryNotExistException;


}
