package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;

public interface ITTopicDao {

	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String des);
}
