package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;

public interface ITStoryDao {

	public void save (TsscStory admin);
	public void delete(TsscStory admin);
	public TsscStory update (TsscStory admin);
	public List<TsscStory> findById(long id);
}
