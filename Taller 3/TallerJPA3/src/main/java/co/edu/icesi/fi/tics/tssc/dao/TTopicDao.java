package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;

@Repository
@Scope("singleton")
public class TTopicDao implements ITTopicDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<TsscTopic> findByName(String name) {
		String jpql = "Select t from TsscTopic t WHERE t.name ='"+name+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String des) {
		String jpql = "Select t from TsscTopic t WHERE t.description ='"+des+"'";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	
	

}
