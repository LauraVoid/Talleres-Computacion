package co.edu.icesi.fi.tics.tssc.testDao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.fi.tics.tssc.dao.TGameDao;
import co.edu.icesi.fi.tics.tssc.dao.TTopicDao;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;

class TsscTopicDao {
	
	@Autowired
	private TTopicDao topicDao;
	
	@Autowired
	private TGameDao gameDao;
	
	private TsscTopic topic;

	@BeforeEach
	void setUp() throws Exception {
		topic= new TsscTopic();
		 topic = new TsscTopic();
			topic.setName("topic1");
			topic.setDescription("Des1");
			topic.setDefaultGroups(10);
			topic.setDefaultSprints(10);
			topicDao.save(topic);
	}
	void setUp2() {
		TsscGame game = new TsscGame();
		game.setName("Game");
		game.setNGroups(20);
		game.setNSprints(20);
		game.setScheduledDate(LocalDate.of(2020, 01, 10));
		game.setScheduledTime(LocalTime.of(10, 23));
		game.setTsscTopic(topic);
		gameDao.save(game);
		
		TsscGame game2 = new TsscGame();
		game2.setName("Game2");
		game2.setNGroups(20);
		game2.setNSprints(20);
		game2.setScheduledDate(LocalDate.of(2020, 01, 10));
		game2.setScheduledTime(LocalTime.of(10, 05));
		game2.setTsscTopic(topic);
		gameDao.save(game2);
		
		
	}

	@Test
	void test1() {
		List<TsscTopic> list= topicDao.findByName(topic.getName());
		
		assertEquals(list.get(0).getName(), "topic1");
		
	}
	@Test
	void test2() {
		List<TsscTopic> list= topicDao.findByDescription(topic.getDescription());
		
		assertEquals(list.get(0).getDescription(), "Des1");
		
	}
	
	@Test
	void test3() {
		setUp2();
		
		assertEquals(2, gameDao.findByDateTopic(LocalDate.of(2020, 01, 10)).size());
	}

}
