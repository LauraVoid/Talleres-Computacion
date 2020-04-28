package co.edu.icesi.fi.tics.tssc.services;

import co.edu.icesi.fi.tics.tssc.modelo.TsscSprint;


public interface TsscSprintService {
	
	public TsscSprint createStory(TsscSprint story);
	public TsscSprint getTsscSprint(long id);
	public void delete(TsscSprint story);
	

}
