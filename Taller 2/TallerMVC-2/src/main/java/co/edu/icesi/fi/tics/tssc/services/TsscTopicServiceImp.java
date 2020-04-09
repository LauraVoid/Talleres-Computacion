package co.edu.icesi.fi.tics.tssc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.exceptions.TopicNoExistsException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicSaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repositories.TsscTopicRepository;

@Service
public class TsscTopicServiceImp implements TsscTopicService {

	public TsscTopicRepository tsscTopicRespository;

	@Autowired
	public TsscTopicServiceImp(TsscTopicRepository tsscTopicRepo) {
		tsscTopicRespository = tsscTopicRepo;
	}

	@Override
	public TsscTopic createTopic(TsscTopic story) throws TopicSaveException {

		if (story == null) {
			throw new TopicSaveException(3);
		} else {
			if (story.getDefaultGroups() <= 0 ) {
				throw new TopicSaveException(1);
			}else if(story.getDefaultSprints() <= 0) {
				throw new TopicSaveException(2);
			}			
			else {
				tsscTopicRespository.save(story);
			}
		}

		return story;
	}

	@Override
	public TsscTopic getTsscTopic(long id) {
		return tsscTopicRespository.findById(id).get();
	}

	@Override
	public TsscTopic updateTsscTopic(TsscTopic topic) throws TopicNoExistsException, TopicSaveException {

		if (topic == null) {
			throw new TopicSaveException(3);
		} else {
			
			
			if (tsscTopicRespository.findById(topic.getId()).isPresent() == false) {
				throw new TopicNoExistsException();
			} else {
				if (topic.getDefaultGroups() <= 0 ) {
					throw new TopicSaveException(1);
				}else if(topic.getDefaultSprints() <= 0) {
					throw new TopicSaveException(2);
				}
			}
			return tsscTopicRespository.save(topic);
		}

	}

	@Override
	public Iterable<TsscTopic> findAll() {
		return tsscTopicRespository.findAll();
	}

	@Override
	public Optional<TsscTopic> findById(long id) {
		return tsscTopicRespository.findById(id);
	}

}
