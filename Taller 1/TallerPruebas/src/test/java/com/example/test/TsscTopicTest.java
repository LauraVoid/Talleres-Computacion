package com.example.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeTest;

import com.example.exceptions.TopicSaveException;
import com.example.modelo.TsscTopic;
import com.example.repositories.TsscTopicRepository;

import com.example.services.TsscTopicServiceImp;

@RunWith(MockitoJUnitRunner.class)
class TsscTopicTest {

		
	private TsscTopic tsscTopic;
	@Mock
	private TsscTopicRepository tsscTopicRepository;
	
	@InjectMocks
	private TsscTopicServiceImp tsscTopicService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	
	}
	
	@Test
	public void createTopicTestThrowsException() {
		//setUp();
		tsscTopic= new TsscTopic();	
		
		tsscTopic.setDefaultSprints(0);
		tsscTopic.setDefaultGroups(0);
		try {
			tsscTopicService.createTopic(tsscTopic);
		}catch(TopicSaveException e ){
			assertTrue(true);
		}	
		
		verify(tsscTopicRepository,times(0)).save(tsscTopic);	
	
	}
	
	@Test
	public void createTopicTestNoThrowsException() {
		//setUp();
		tsscTopic= new TsscTopic();
		
		tsscTopic.setDefaultSprints(1);
		tsscTopic.setDefaultGroups(1);		
		try {			
			when(tsscTopicRepository.save(tsscTopic)).thenReturn(tsscTopic);
			assertTrue(tsscTopicService.createTopic(tsscTopic).equals(tsscTopic));
			verify(tsscTopicRepository,times(1)).save(tsscTopic);
			
		}catch(TopicSaveException e){
			fail();
		}	
		
		
	}
	
	public void updateTopicTestThrowsException() {
		tsscTopic = new TsscTopic();
		
	}

}
