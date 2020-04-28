package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalTime;
import java.util.List;

import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;

public interface ITGameDao {

	public List<TsscGame>findByName(String name);
	public List<TsscGame>findByDescription(String des);
	public List<TsscGame>findByTopicId(long id);
	public List<TsscGame>findByDate(LocalTime init, LocalTime end);
}
