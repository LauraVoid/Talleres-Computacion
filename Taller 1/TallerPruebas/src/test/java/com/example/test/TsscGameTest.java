package com.example.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.exceptions.GameSaveException;
import com.example.exceptions.TopicNoExistsException;
import com.example.modelo.TsscGame;
import com.example.modelo.TsscTopic;
import com.example.repositories.TsscGameRepository;
import com.example.repositories.TsscTopicRepository;
import com.example.services.TsscGameServiceImp;

@RunWith(MockitoJUnitRunner.class)
class TsscGameTest {
	
	private TsscGame tsscGame;
	private TsscTopic tsscTopic;
	
	@Mock
	private TsscTopicRepository tsscTopicRepository;
	
	@Mock
	private TsscGameRepository tsscGameRepository;
	
	@InjectMocks
	private TsscGameServiceImp tsscGameServiceImp;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		tsscGame= new TsscGame();
		tsscTopic= new TsscTopic();
		
	}

	@Test
	public void createGameTestThrowsException() {
		
		tsscGame.setNGroups(0);
		tsscGame.setNSprints(0);
		tsscTopicRepository.save(tsscTopic);
		
		try {
			tsscGameServiceImp.createGame(tsscGame,tsscTopic.getId());
			
		}catch(GameSaveException | TopicNoExistsException e) {
			assertTrue(true);
		}
		verify(tsscGameRepository, times(0)).save(tsscGame);
		
	}
	
	@Test
	public void createGameTestNoThrowsException() {
		
		
		//TsscTopic ts=tsscTopicRepository.save(tsscTopic);
		
		
		//TsscTopic found= tsscTopicRepository.findById(tsscTopic.getId()).get();
		//System.out.println("in prueba"+found.getId());
		tsscGame.setNGroups(1);
		tsscGame.setNSprints(1);
		
		tsscGame.setTsscTopic(tsscTopic);
		
		try {
				
			tsscTopicRepository.save(tsscTopic);
			when(tsscGameRepository.save(tsscGame)).thenReturn(tsscGame);
			assertTrue(tsscGameServiceImp.createGame(tsscGame,tsscTopic.getId()).equals(tsscGame));
			verify(tsscGameRepository, times(1)).save(tsscGame);
			
		}catch(GameSaveException | TopicNoExistsException e) {
			System.out.println(e.getMessage());
			fail();
		}
		
		
	}
	@Test
	public void createGameTopicNullTest() {	
		
		tsscGame.setNGroups(1);
		tsscGame.setNSprints(1);
		
		try {
			tsscGameServiceImp.createGame(tsscGame,tsscTopic.getId());			
			
		}catch(TopicNoExistsException | GameSaveException e){
			assertTrue(true);
		}
		verify(tsscGameRepository, times(0)).save(tsscGame);
		//verifyZeroInteractions(TsscTopicRepository);
		
		
		
	}
	@Test
	public void createGameTopicNullTestThrowsException() {	
		
		tsscGame.setNGroups(0);
		tsscGame.setNSprints(0);
		
		try {
			tsscGameServiceImp.createGame(tsscGame,tsscTopic.getId());			
			
		}catch(TopicNoExistsException | GameSaveException e){
			assertTrue(true);
		}
		verify(tsscGameRepository, times(0)).save(tsscGame);
		//verifyZeroInteractions(TsscTopicRepository);
		
		
		
	}


}
