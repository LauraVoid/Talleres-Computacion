package co.edu.icesi.fi.tics.tssc.testDao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.TallerMvc2Application;
import co.edu.icesi.fi.tics.tssc.dao.ITGameDao;
import co.edu.icesi.fi.tics.tssc.dao.TGameDao;
import co.edu.icesi.fi.tics.tssc.dao.TTopicDao;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TsscGameDao {

	@Autowired
	private TGameDao gameDao;
	
	@Autowired
	private TTopicDao topicDao;
	
	private TsscTopic topic; 
	private TsscGame game;
	@BeforeEach
	void setUp() throws Exception {
		
		 topic = new TsscTopic();
		topic.setName("topic1");
		topic.setDescription("Des1");
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		topicDao.save(topic);
		
		game =new TsscGame();
		game.setName("Game");
		game.setTsscTopic(topic);
		game.setNGroups(20);
		game.setNSprints(20);
		game.setScheduledDate(LocalDate.of(2020, 01, 10));
		game.setScheduledTime(LocalTime.of(10, 23));
		gameDao.save(game);
	}
	void setUp2() {
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
	public void test() {
		
		
		List<TsscGame> lista= gameDao.findByName("Game");
		
		//System.out.println(lista.get(0).getName());
		assertTrue(lista.get(0).getName().equals("Game"));
	}
	@Test
	public void test2() {		
		
		List<TsscGame> lista= gameDao.findByDescription("Des1");
		
		//System.out.println(lista.get(0).getName());
		assertTrue(lista.get(0).getName().equals("Game"));
	}
	@Test
	public void test3() {		
		
		List<TsscGame> lista= gameDao.findByTopicId(topic.getId());
		
		//System.out.println(lista.get(0).getName());
		assertTrue(lista.get(0).getName().equals("Game"));
	}
	@Test
	public void test4() {		
		
		List<TsscGame> lista= gameDao.findByDate(LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 20));
		
		//System.out.println(lista.get(0).getName());
		assertTrue(lista.get(0).getName().equals("Game"));
	}
	@Test
	public void test5() {		
		
		List<TsscGame> lista= gameDao.findByTime(LocalDate.of(2020, 01, 01),  LocalDate.of(2020, 01, 20), LocalTime.of(10, 23));
		
		assertTrue(lista.get(0).getName().equals("Game"));
	}
	@Test
	public void test6() {		
		
		List<TsscGame> lista= gameDao.findByLessStories(LocalDate.of(2020, 01, 10));
		
		assertTrue(lista.get(0).getName().equals("Game"));
	}
	


}
