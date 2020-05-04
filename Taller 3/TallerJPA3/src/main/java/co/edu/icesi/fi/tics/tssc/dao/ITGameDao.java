package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;

public interface ITGameDao {

	public List<TsscGame>findByName(String name);
	public List<TsscGame>findByDescription(String des);
	public List<TsscGame>findByTopicId(long id);
	public List<TsscGame>findByDate(LocalDate init, LocalDate end);
	public List<TsscGame>findByTime(LocalDate hourInit, LocalDate hourFini, LocalTime time);
	public List<TsscTopic>findByDateTopic(LocalDate date);
	public List<TsscGame>findByLessStories(LocalDate date);
	public void save(TsscGame game);
}
