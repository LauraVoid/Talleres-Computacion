package co.edu.icesi.fi.tics.tssc.test;

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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.edu.icesi.fi.tics.tssc.modelo.TsscGame;
import co.edu.icesi.fi.tics.tssc.modelo.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repositories.TsscGameRepository;
import co.edu.icesi.fi.tics.tssc.repositories.TsscTopicRepository;
import co.edu.icesi.fi.tics.tssc.services.TsscGameServiceImp;
import co.edu.icesi.fi.tics.tssc.exceptions.*;

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
		tsscGame = new TsscGame();

	}

	@Test
	public void createGameTestThrowsException() {
		tsscTopic = new TsscTopic();
		tsscGame.setNGroups(0);
		tsscGame.setNSprints(0);

		try {
			tsscGameServiceImp.createGame(tsscGame, tsscTopic.getId());

		} catch (GameSaveException | TopicNoExistsException e) {
			assertTrue(true);
		}
		verify(tsscGameRepository, times(0)).save(tsscGame);

	}

	/**
	 * Prueba para verificar que es posible crear un game con el numero de grupos y
	 * el numero de sprint mayor que cero y con un topic asociado que exista
	 */

	@Test
	public void createGameTestNoThrowsException() {

		long id = 1;
		tsscGame.setNGroups(1);
		tsscGame.setNSprints(1);

		try {
			when(tsscTopicRepository.findById(id)).thenReturn(Optional.of(new TsscTopic()));
			when(tsscGameRepository.save(tsscGame)).thenReturn(tsscGame);
			assertTrue(tsscGameServiceImp.createGame(tsscGame, id).equals(tsscGame));
			verify(tsscGameRepository, times(1)).save(tsscGame);

		} catch (GameSaveException | TopicNoExistsException e) {

			fail();
		}

	}

	/**
	 * Prueba para verificar que no es posible agregar un Game nulo
	 */
	@Test
	public void createGameNull() {
		long id = 0;

		try {
			tsscGameServiceImp.createGame(null, id);
		} catch (GameSaveException | TopicNoExistsException e) {
			assertTrue(true);

		}
		verifyZeroInteractions(tsscGameRepository);
	}

	/**
	 * Prueba para verificar si no es posible agregar un juego con un tema no
	 * existente y los numeros de grupos y numero de sprint mayor que cero
	 */
	@Test
	public void createGameTopicNullTest() {
		tsscTopic = new TsscTopic();
		tsscGame.setNGroups(1);
		tsscGame.setNSprints(1);
		long id = 1;
		try {
			when(tsscTopicRepository.findById(id)).thenReturn(Optional.empty());
			tsscGameServiceImp.createGame(tsscGame, id);

		} catch (TopicNoExistsException | GameSaveException e) {
			assertTrue(true);
		}
		verify(tsscGameRepository, times(0)).save(tsscGame);

	}

	@Test
	public void createGameTopicNullTest2() {
		tsscTopic = new TsscTopic();
		tsscGame.setNGroups(0);
		tsscGame.setNSprints(0);
		long id = 1;

		try {
			when(tsscTopicRepository.findById(id)).thenReturn(Optional.empty());
			tsscGameServiceImp.createGame(tsscGame, tsscTopic.getId());

		} catch (TopicNoExistsException | GameSaveException e) {
			
			assertTrue(true);
		}
		verify(tsscGameRepository, times(0)).save(tsscGame);

	}

	/**
	 * Prueba para verificar si realiza correctamente una actualizacion con todos
	 * los requisitos correctos
	 */
	@Test
	public void UpdateGameNoThrowException() {
		tsscTopic = new TsscTopic();
		tsscGame.setNGroups(99);
		tsscGame.setNSprints(99);

		Optional<TsscGame> optional = Optional.of(tsscGame);
		when(tsscGameRepository.save(Mockito.any())).thenReturn(tsscGame);
		when(tsscGameRepository.findById(tsscGame.getId())).thenReturn(optional);

		try {
			assertTrue(tsscGameServiceImp.updateTsscGame(tsscGame).equals(tsscGame));
		} catch (GameSaveException | GameNotEsxistException e) {
			fail();

		}
		verify(tsscGameRepository, times(1)).save(tsscGame);

	}

	/**
	 * Prueba para verificar que al actualizar un Game con sprint y groups igual a
	 * cero no permite guardar la actualización de game
	 */
	@Test
	public void UpdateGameThrowException() {
		tsscTopic = new TsscTopic();
		tsscGame.setNGroups(0);
		tsscGame.setNSprints(0);

		try {
			
			tsscGameServiceImp.updateTsscGame(tsscGame);
		} catch (GameSaveException | GameNotEsxistException e) {
			assertTrue(true);

		}
		verify(tsscGameRepository, times(0)).save(tsscGame);

	}

	/**
	 * Prueba para verificar que no permite actualizar un game que no existe,
	 * teniendo el número de grupos y el número de sprint mayor que cero
	 */
	@Test
	public void UpdateGameThrowException2() {
		tsscTopic = new TsscTopic();
		tsscGame.setNGroups(99);
		tsscGame.setNSprints(99);
		long id = 1;
		when(tsscGameRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(GameNotEsxistException.class, () -> {
			tsscGameServiceImp.updateTsscGame(tsscGame);
		});
		verify(tsscGameRepository, times(0)).save(tsscGame);

	}

	/**
	 * Prueba para verificar que al actualizar un Game con sprint y groups menor que
	 * cero no permite guardar la actualización de game
	 */
	@Test
	public void UpdateGameThrowException3() {
		tsscTopic = new TsscTopic();
		tsscGame.setNGroups(-99);
		tsscGame.setNSprints(-99);

		try {
			Optional<TsscGame> optional = Optional.of(tsscGame);
			when(tsscGameRepository.save(Mockito.any())).thenReturn(tsscGame);
			when(tsscGameRepository.findById(tsscGame.getId())).thenReturn(optional);
			tsscGameServiceImp.updateTsscGame(tsscGame);
		} catch (GameSaveException | GameNotEsxistException e) {
			assertTrue(true);

		}
		verify(tsscGameRepository, times(0)).save(tsscGame);

	}

	/**
	 * Prueba para verificar que no permite actualizar un game que no existe,
	 * teniendo el número de grupos y el número de sprint menor que cero
	 */
	@Test
	public void UpdateGameThrowException4() {
		tsscTopic = new TsscTopic();
		tsscGame.setNGroups(-99);
		tsscGame.setNSprints(-99);
		long id = 1;
		when(tsscTopicRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(GameNotEsxistException.class, () -> {
			tsscGameServiceImp.updateTsscGame(tsscGame);
		});
		verify(tsscGameRepository, times(0)).save(tsscGame);

	}

}
