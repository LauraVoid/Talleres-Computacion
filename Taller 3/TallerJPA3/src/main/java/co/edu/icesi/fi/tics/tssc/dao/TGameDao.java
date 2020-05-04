package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;
@Repository
@Scope("singleton")
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
	public List<TsscGame> findByDate(LocalDate init, LocalDate end) {
		String jpql = "Select t from TsscGame t WHERE t.scheduledDate BETWEEN  '"+init+"' and '"+end+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(TsscGame game) {
		entityManager.persist(game);
		
	}

	@Override
	public List<TsscGame> findByTime(LocalDate hourInit, LocalDate hourFini, LocalTime time) {
		String jpql = "Select t from TsscGame a WHERE t.scheduledDate BETWEEN '"+hourInit+"' AND '"+hourFini+"' AND t.scheduledDate = '"+time+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTopic> findByDateTopic(LocalDate date) {
		String jpql = "Select s.tsscTopic, count(s) from TsscGame s "
				+ "where s.tsscTopic.scheduledDate = '"+date+"' "
						+ "group by s.tsscTopic ORDER BY s.scheduledTime ASC ";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByLessStories(LocalDate date) {
		String jpql = "Select a from TsscGame a "
				+ "Where a.scheduledDate ='"+date+"'"
						+ "  AND (SELECT Count(b) FROM TsscTimecontrol b WHERE b.tsscGame.id = a.id) = 0) "
							+ "OR (SELECT Count(s) FROM TsscStory s WHERE s.tsscGame.id = a.id ) < 10))";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	
	

	
}
