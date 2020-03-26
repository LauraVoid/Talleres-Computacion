package com.example.services;

import com.example.modelo.TsscSprint;


public interface TsscSprintService {
	
	public TsscSprint createStory(TsscSprint story);
	public TsscSprint getTsscSprint(long id);
	public void deleteTsscStory(TsscSprint story);
	

}
