package co.edu.icesi.fi.tics.tssc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;

@Repository
public interface TsscTopicRepository extends CrudRepository<TsscTopic, Long>{

}
