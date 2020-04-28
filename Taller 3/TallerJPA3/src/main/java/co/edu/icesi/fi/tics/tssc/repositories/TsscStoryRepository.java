package co.edu.icesi.fi.tics.tssc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;
@Repository
public interface TsscStoryRepository extends CrudRepository<TsscStory,Long> {

}
