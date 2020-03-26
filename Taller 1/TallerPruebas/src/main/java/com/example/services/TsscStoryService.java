package com.example.services;

import com.example.modelo.TsscStory;

public interface TsscStoryService {
	
	public TsscStory createStory(TsscStory story);
	public TsscStory getTsscStory(long id);
	public void deleteTsscStory(TsscStory story);

}
