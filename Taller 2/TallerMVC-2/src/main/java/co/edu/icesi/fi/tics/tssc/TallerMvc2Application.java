package co.edu.icesi.fi.tics.tssc;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import co.edu.icesi.fi.tics.tssc.exceptions.GameNotEsxistException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameSaveException;
import co.edu.icesi.fi.tics.tssc.exceptions.StorySaveException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicSaveException;
import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscStory;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;
import co.edu.icesi.fi.tics.tssc.services.TsscGameServiceImp;
import co.edu.icesi.fi.tics.tssc.services.TsscStoryServiceImp;
import co.edu.icesi.fi.tics.tssc.services.TsscTopicServiceImp;

@SpringBootApplication
public class TallerMvc2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext c=SpringApplication.run(TallerMvc2Application.class, args);
		TsscTopicServiceImp topic= c.getBean(TsscTopicServiceImp.class);
		TsscGameServiceImp game= c.getBean(TsscGameServiceImp.class);
		TsscStoryServiceImp story= c.getBean(TsscStoryServiceImp.class);
		
		TsscTopic t= new TsscTopic();
		t.setName("Topic1");
		t.setGroupPrefix("10");
		t.setDefaultGroups(10);
		t.setDefaultSprints(10);
		
		TsscTopic t2= new TsscTopic();
		t2.setName("Topic2");
		t2.setGroupPrefix("10");
		t2.setDefaultGroups(10);
		t2.setDefaultSprints(10);
		
		try {
			topic.createTopic(t);
			topic.createTopic(t2);
		} catch (TopicSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		TsscGame g1= new TsscGame();
		g1.setNGroups(10);
		g1.setNSprints(100);
		g1.setName("juego1");
		
		TsscGame g2= new TsscGame();
		g2.setNGroups(880);
		g2.setNSprints(100);
		g2.setName("juego2");		
		
		try {
			game.createGame(g1);
			game.createGame(g2);
		} catch (GameSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TsscStory s= new TsscStory();
		s.setBusinessValue(BigDecimal.TEN);
		s.setInitialSprint(BigDecimal.TEN);
		s.setPriority(BigDecimal.TEN);
		s.setAltDescShown("story1");
		s.setTsscGame(g1);
		long id= g1.getId();
		
		TsscStory s2= new TsscStory();
		s2.setBusinessValue(BigDecimal.TEN);
		s2.setInitialSprint(BigDecimal.TEN);
		s2.setPriority(BigDecimal.TEN);
		s2.setAltDescShown("story2");
		s2.setTsscGame(g2);
		long id2= g2.getId();
		
		try {
			story.createStory(s, id);
			story.createStory(s2, id2);
		} catch (StorySaveException | GameNotEsxistException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	
	}

}
