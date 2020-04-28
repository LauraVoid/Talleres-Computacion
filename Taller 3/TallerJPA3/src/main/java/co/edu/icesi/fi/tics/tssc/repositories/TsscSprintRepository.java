package co.edu.icesi.fi.tics.tssc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.modelo.TsscSprint;
@Repository
public interface TsscSprintRepository extends CrudRepository<TsscSprint, Long>{

}
