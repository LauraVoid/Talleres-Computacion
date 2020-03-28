package co.edu.icesi.fi.tics.tssc.integracion;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.fi.tics.tssc.exceptions.GameNotEsxistException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameSaveException;
import co.edu.icesi.fi.tics.tssc.exceptions.StoryNotExistException;
import co.edu.icesi.fi.tics.tssc.exceptions.StorySaveException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicNoExistsException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;
import co.edu.icesi.fi.tics.tssc.services.TsscGameServiceImp;
import co.edu.icesi.fi.tics.tssc.services.TsscStoryServiceImp;

@SpringBootTest
class StoryTest {

	private TsscStory story;
	private TsscGame game;
	
	@Autowired
	private TsscStoryServiceImp tsscStoryService;
	@Autowired
	private TsscGameServiceImp tsscGameService;
	
	@BeforeEach
	public void setUp() throws Exception {
		story= new TsscStory();
		game= new TsscGame();		
		
		game.setNGroups(1);
		game.setNSprints(1);
		
		story.setInitialSprint(BigDecimal.TEN);
		story.setBusinessValue(BigDecimal.TEN);
		story.setPriority(BigDecimal.TEN);
		
	}

	@Test
	public void createStoryTest() {
		
		long id=0;
		try {
			tsscGameService.createGame(game, id);			
			assertEquals(tsscStoryService.createStory(story, game.getId()), story);
		} catch (GameSaveException | TopicNoExistsException | StorySaveException | GameNotEsxistException e) {
			
		}
		
	}
	@Test
	public void updateStoryTest() {
		
		try {
			tsscStoryService.createStory(story, game.getId());			
			story.setInitialSprint(BigDecimal.ONE);
			story.setBusinessValue(BigDecimal.ONE);
			story.setPriority(BigDecimal.ONE);
			
			assertEquals(tsscStoryService.updateStory(story), story);
		} catch (StorySaveException | GameNotEsxistException | StoryNotExistException e) {
			
		}
		
		
	}

}
