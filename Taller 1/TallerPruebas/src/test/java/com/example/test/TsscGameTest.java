package com.example.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
		
		
	}

	@Test
	public void createGameTestThrowsException() {
		tsscTopic= new TsscTopic();
		tsscGame.setNGroups(0);
		tsscGame.setNSprints(0);
		
		
		try {
			tsscGameServiceImp.createGame(tsscGame,tsscTopic.getId());
			
		}catch(GameSaveException | TopicNoExistsException e) {
			assertTrue(true);
		}
		verify(tsscGameRepository, times(0)).save(tsscGame);
		
	}
	
	@Test
	public void createGameTestNoThrowsException() {
			
		long id=1;
		tsscGame.setNGroups(1);
		tsscGame.setNSprints(1);
	
		try {				
			when(tsscTopicRepository.findById(id)).thenReturn(Optional.of(new TsscTopic()));			
			when(tsscGameRepository.save(tsscGame)).thenReturn(tsscGame);
			assertTrue(tsscGameServiceImp.createGame(tsscGame,id).equals(tsscGame));
			verify(tsscGameRepository, times(1)).save(tsscGame);
			
		}catch(GameSaveException | TopicNoExistsException e) {
			System.out.println(e.getMessage());
			fail();
		}
		
		
	}
	@Test
	public void createGameTopicNullTest() {	
		tsscTopic= new TsscTopic();
		tsscGame.setNGroups(1);
		tsscGame.setNSprints(1);
		
		try {
			tsscGameServiceImp.createGame(tsscGame,tsscTopic.getId());			
			
		}catch(TopicNoExistsException | GameSaveException e){
			assertTrue(true);
		}
		verify(tsscGameRepository, times(0)).save(tsscGame);
		
		
		
		
	}
	@Test
	public void createGameTopicNullTestThrowsException() {	
		tsscTopic= new TsscTopic();
		tsscGame.setNGroups(0);
		tsscGame.setNSprints(0);
		
		try {
			tsscGameServiceImp.createGame(tsscGame,tsscTopic.getId());			
			
		}catch(TopicNoExistsException | GameSaveException e){
			assertTrue(true);
		}
		verify(tsscGameRepository, times(0)).save(tsscGame);
	
		
		
		
	}
	/*
	 * Prueba para verificar si realiza correctamente una actualizacion con
	 * todos los requisitos correctos
	 */
	@Test
	public void UpdateGame() {		
		tsscTopic= new TsscTopic();
		tsscGame.setNGroups(99);
		tsscGame.setNSprints(99);
		
		
		
		
		
	}
	


}
