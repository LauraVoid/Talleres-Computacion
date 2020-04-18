package co.edu.icesi.fi.tics.tssc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.modelo.TsscAdmin;

@Repository
public interface TsscAdminRepository extends CrudRepository<TsscAdmin, Long>{

	  TsscAdmin findByUser(String user);
	
}
