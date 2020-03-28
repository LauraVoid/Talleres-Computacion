package co.edu.icesi.fi.tics.tssc.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeTest;

import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repositories.TsscTopicRepository;
import co.edu.icesi.fi.tics.tssc.services.TsscTopicServiceImp;
import co.edu.icesi.fi.tics.tssc.exceptions.*;

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
		tsscTopic = new TsscTopic();

	}

	@Test
	public void createTopicTestThrowsException() {

		tsscTopic.setDefaultSprints(0);
		tsscTopic.setDefaultGroups(0);
		try {
			tsscTopicService.createTopic(tsscTopic);
		} catch (TopicSaveException e) {
			assertTrue(true);
		}

		verify(tsscTopicRepository, times(0)).save(tsscTopic);

	}

	@Test
	public void createTopicTestNull() {

		try {
			tsscTopicService.createTopic(null);
		} catch (TopicSaveException e) {
			assertTrue(true);
		}

		verify(tsscTopicRepository, times(0)).save(tsscTopic);
	}

	@Test
	public void createTopicTestThrowsExceptionLessZero() {
		tsscTopic.setDefaultSprints(-15);
		tsscTopic.setDefaultGroups(-99);
		try {
			tsscTopicService.createTopic(tsscTopic);
		} catch (TopicSaveException e) {
			assertTrue(true);
		}

		verify(tsscTopicRepository, times(0)).save(tsscTopic);

	}

	@Test
	public void createTopicTestNoThrowsException() {

		tsscTopic.setDefaultSprints(1);
		tsscTopic.setDefaultGroups(1);
		try {
			when(tsscTopicRepository.save(tsscTopic)).thenReturn(tsscTopic);
			assertTrue(tsscTopicService.createTopic(tsscTopic).equals(tsscTopic));
			verify(tsscTopicRepository, times(1)).save(tsscTopic);

		} catch (TopicSaveException e) {
			fail();
		}

	}

	@Test
	public void updateTopicTestThrowsNotExistException() {

		long id = 3;
		when(tsscTopicRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(TopicNoExistsException.class, () -> {
			tsscTopicService.updateTsscTopic(tsscTopic);
		});

		verify(tsscTopicRepository, times(0)).save(tsscTopic);

	}

	@Test
	public void updateTopicTestThrowsTopicException() {
		tsscTopic.setDefaultSprints(0);
		tsscTopic.setDefaultGroups(0);

		try {
			Optional<TsscTopic> opcional = Optional.of(tsscTopic);
			when(tsscTopicRepository.save(Mockito.any())).thenReturn(tsscTopic);
			when(tsscTopicRepository.findById(tsscTopic.getId())).thenReturn(opcional);
			tsscTopicService.updateTsscTopic(tsscTopic);
		} catch (TopicSaveException | TopicNoExistsException e) {
			assertTrue(true);
		}

		verify(tsscTopicRepository, times(0)).save(tsscTopic);

	}

	/**
	 * Prueba para verificar si con Sprint y Groups igual a cero y un Topic que no
	 * esta guardado rechaza la actualización del topic
	 */
	@Test
	public void updateTopicTestThrowsNotExistException2() {
		tsscTopic.setDefaultSprints(0);
		tsscTopic.setDefaultGroups(0);
		long id = 3;
		when(tsscTopicRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(TopicNoExistsException.class, () -> {
			tsscTopicService.updateTsscTopic(tsscTopic);
		});

	}

	/**
	 * Prueba para verificar si al tener todas las variables correctamente permite
	 * actualizar el topic
	 */
	@Test
	public void updateTopicTestNoThrowsException() {
		tsscTopic.setDefaultSprints(99);
		tsscTopic.setDefaultGroups(99);

		Optional<TsscTopic> opcional = Optional.of(tsscTopic);

		when(tsscTopicRepository.save(Mockito.any())).thenReturn(tsscTopic);
		when(tsscTopicRepository.findById(tsscTopic.getId())).thenReturn(opcional);
		try {
			assertTrue(tsscTopicService.updateTsscTopic(tsscTopic).equals(tsscTopic));
		} catch (TopicNoExistsException | TopicSaveException e) {
			fail();
			e.printStackTrace();
		}
		verify(tsscTopicRepository, times(1)).save(tsscTopic);

	}

	@Test
	public void updateTopicTestNull() {
		try {
			tsscTopicService.updateTsscTopic(null);
		} catch (TopicNoExistsException | TopicSaveException e) {
			assertTrue(true);

		}
		verify(tsscTopicRepository, times(0)).save(tsscTopic);

	}

}
