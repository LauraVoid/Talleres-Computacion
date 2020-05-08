package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.modelo.TsscAdmin;

@Repository
@Scope("singleton")
public class TAdminDao implements ITAdminDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscAdmin admin) {
		entityManager.persist(admin);
		
	}

	@Override
	public void delete(TsscAdmin admin) {
		entityManager.remove(admin);
		
	}

	@Override
	public TsscAdmin update(TsscAdmin admin) {
		
		return entityManager.merge(admin);
	}

	@Override
	public List<TsscAdmin> findById(long id) {
		String jpql = "Select t from TsscAdmin t WHERE t.id ='"+id+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

}
