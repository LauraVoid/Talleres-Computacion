package co.edu.icesi.fi.tics.tssc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
@Repository
public interface TsscGameRepository extends CrudRepository<TsscGame, Long>{

}
