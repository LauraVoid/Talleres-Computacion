package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.modelo.TsscTimecontrol;

@Repository
@Scope("singleton")
public class TTimeControlDao implements ITTimeControlDao {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(TsscTimecontrol admin) {
		entityManager.persist(admin);
		
	}

	@Override
	public void delete(TsscTimecontrol admin) {
		entityManager.remove(admin);
		
	}

	@Override
	public TsscTimecontrol update(TsscTimecontrol admin) {
		return entityManager.merge(admin);
	}

	@Override
	public List<TsscTimecontrol> findById(long id) {
		String jpql = "Select t from TsscAdmin t WHERE t.id ='"+id+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

}
