package com.example.integracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.TallerPruebasApplication;
import com.example.exceptions.TopicNoExistsException;
import com.example.exceptions.TopicSaveException;
import com.example.modelo.TsscTopic;
import com.example.services.TsscTopicServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TallerPruebasApplication.class)
public class TopicTest {

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
		topic2.setDefaultGroups(1);
		topic2.setDefaultSprints(1);
		topic2.setId(topic.getId());
		try {
			tsscTopicService.createTopic(topic);

			assertTrue(tsscTopicService.updateTsscTopic(topic2).equals(topic2));
		} catch (TopicSaveException | TopicNoExistsException e) {
			fail();
		}

	}

}
