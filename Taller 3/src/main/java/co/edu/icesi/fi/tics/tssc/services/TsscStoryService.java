package co.edu.icesi.fi.tics.tssc.services;


import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.exceptions.GameNotEsxistException;
import co.edu.icesi.fi.tics.tssc.exceptions.StoryNotExistException;
import co.edu.icesi.fi.tics.tssc.exceptions.StorySaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;

public interface TsscStoryService {
	
	public TsscStory createStory(TsscStory story, long game)throws StorySaveException, GameNotEsxistException;
	public TsscStory updateStory(TsscStory story)throws StorySaveException,StoryNotExistException;
	public Iterable<TsscStory> findAll();
	public Optional<TsscStory>findById(long id);
	public void delete(TsscStory story);


}
