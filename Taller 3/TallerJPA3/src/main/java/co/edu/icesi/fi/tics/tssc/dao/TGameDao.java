package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;

public class TGameDao implements ITGameDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<TsscGame> findByName(String name) {
		String jpql = "Select t from TsscGame t WHERE t.name ='"+name+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDescription(String des) {
		String jpql = "Select t from TsscGame t WHERE t.tsscTopic.description ='"+des+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByTopicId(long id) {
		String jpql = "Select t from TsscGame t WHERE t.tsscTopic.id ='"+id+"'";
		return  entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDate(LocalTime init, LocalTime end) {
		String jpql = "Select t from TsscGame t WHERE t.scheduledDate between '"+init+"' and '"+end+"'";
		return null;
	}
	

	
}
