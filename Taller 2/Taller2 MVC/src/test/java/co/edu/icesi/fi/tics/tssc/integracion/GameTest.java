package co.edu.icesi.fi.tics.tssc.integracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.icesi.fi.tics.tssc.exceptions.GameNotEsxistException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameSaveException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicNoExistsException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicSaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;
import co.edu.icesi.fi.tics.tssc.services.TsscGameServiceImp;
import co.edu.icesi.fi.tics.tssc.services.TsscTopicServiceImp;

@SpringBootTest

class GameTest {

	
	private TsscGame game;
	private TsscTopic topic;
	
	@Autowired
	private TsscGameServiceImp tsscGameService;
	@Autowired
	private TsscTopicServiceImp tsscTopicService;
	
	@BeforeEach
	void setUp() throws Exception {
		game= new TsscGame();
		topic = new TsscTopic();
		
		game.setNGroups(1);
		game.setNSprints(1);
		
		topic.setDefaultSprints(99);
		topic.setDefaultGroups(99);
		
		tsscTopicService.createTopic(topic);
	}

	@Test
	public void createGameTest() {
		
		try {
			
			assertEquals(tsscGameService.createGame(game), game);
		} catch ( GameSaveException e) {
			fail();
		}
	}
	@Test
	public void createGame2Test() {
		
	try {
			
			assertEquals(tsscGameService.createGameTopic(game, topic.getId()), game);
		} catch ( GameSaveException | TopicNoExistsException e) {
			fail();
		}
		
	}
	@Test
	public void updateGameTest() {
		 TsscGame game2= new TsscGame();
		String name="game2";
		
		try {
			game2.setId(game.getId());
			game2.setName(name);
			game2.setNGroups(10);
			game2.setNSprints(10);
			
			tsscGameService.createGameTopic(game, topic.getId());
			assertEquals(tsscGameService.updateTsscGame(game2).getName(), name);
		} catch ( GameSaveException | GameNotEsxistException | TopicNoExistsException  e) {
			fail();
		}
	}
	
	

}
