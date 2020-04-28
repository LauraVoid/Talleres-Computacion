package co.edu.icesi.fi.tics.tssc.integracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.fi.tics.tssc.exceptions.TopicNoExistsException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicSaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;
import co.edu.icesi.fi.tics.tssc.services.TsscTopicServiceImp;

@SpringBootTest
class TopicTest {

	private TsscTopic topic;

	@Autowired
	private TsscTopicServiceImp tsscTopicService;

	@BeforeEach
	void setUp() throws Exception {
		topic = new TsscTopic();
		topic.setDefaultSprints(99);
		topic.setDefaultGroups(99);
	}

	@Test
	public void createTopicTest() {

		System.out.println(tsscTopicService);
		try {
			assertEquals(tsscTopicService.createTopic(topic), topic);
		} catch (TopicSaveException e) {
			fail();
		}
	}

	@Test
	public void updateTopicTest() {
		TsscTopic topic2 = new TsscTopic();
		String name= "topic2";
		topic2.setDefaultGroups(1);
		topic2.setDefaultSprints(1);
		
		try {
			tsscTopicService.createTopic(topic);
			topic2.setId(topic.getId());
			topic2.setName(name);

			assertTrue(tsscTopicService.updateTsscTopic(topic2).getName().equals(name));
		} catch (TopicSaveException | TopicNoExistsException e) {
			fail();
		}

	}

}
