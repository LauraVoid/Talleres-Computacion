package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;

@Repository
@Scope("singleton")
public class TStoryDao implements ITStoryDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscStory admin) {
		entityManager.persist(admin);
		
	}

	@Override
	public void delete(TsscStory admin) {
		entityManager.remove(admin);
		
	}

	@Override
	public TsscStory update(TsscStory admin) {
		// TODO Auto-generated method stub
		return entityManager.merge(admin);
	}

	@Override
	public List<TsscStory> findById(long id) {
		String jpql = "Select t from TsscAdmin t WHERE t.id ='"+id+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

}
