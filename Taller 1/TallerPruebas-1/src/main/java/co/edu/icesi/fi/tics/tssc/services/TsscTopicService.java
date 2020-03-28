package co.edu.icesi.fi.tics.tssc.services;

import co.edu.icesi.fi.tics.tssc.exceptions.TopicNoExistsException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicSaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;

public interface TsscTopicService {
	
	public TsscTopic createTopic(TsscTopic story) throws TopicSaveException;
	public TsscTopic getTsscTopic(long id);
	public TsscTopic updateTsscTopic(TsscTopic topic)throws TopicNoExistsException, TopicSaveException;


}
