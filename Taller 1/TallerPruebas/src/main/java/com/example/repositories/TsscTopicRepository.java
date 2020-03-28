package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.modelo.TsscTopic;

@Repository
public interface TsscTopicRepository extends CrudRepository<TsscTopic, Long>{

}
