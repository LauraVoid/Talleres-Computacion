package com.example.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.exceptions.GameNotEsxistException;
import com.example.exceptions.StoryNotExistException;
import com.example.exceptions.StorySaveException;
import com.example.modelo.TsscGame;
import com.example.modelo.TsscStory;
import com.example.modelo.TsscTopic;
import com.example.repositories.TsscGameRepository;
import com.example.repositories.TsscStoryRepository;
import com.example.services.TsscStoryServiceImp;

class TsscStoryTest {

	private TsscStory tsscStory;
	@Mock
	private TsscGameRepository tsscGameRepository;
	@Mock
	private TsscStoryRepository tsscStoryRepository;
	@InjectMocks
	private TsscStoryServiceImp tsscStoryService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		tsscStory = new TsscStory();

	}

	// Test De creación
	/**
	 * Prueba que permite verificar que no es posible agregar un story nulo aunque
	 * existe el game asociado
	 */
	@Test
	public void createStoryNullTest() {
		long id = 1;
		try {
			when(tsscGameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));
			tsscStoryService.createStory(null, id);

		} catch (StorySaveException | GameNotEsxistException e) {
			assertTrue(true);
		}
		verify(tsscStoryRepository, times(0)).save(tsscStory);

	}

	/**
	 * Prueba para verificar, si al agregar un story con Valor de negocio, valor
	 * inicial de sprint y prioridad mayor que cero y un juego asociado que exista,
	 * lo guarda correctamente
	 */
	@Test
	public void createStoryNoThrowException() {

		tsscStory.setBusinessValue(BigDecimal.ONE);
		tsscStory.setInitialSprint(BigDecimal.ONE);
		tsscStory.setPriority(BigDecimal.ONE);
		long id = 1;
		try {
			when(tsscGameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));
			when(tsscStoryRepository.save(tsscStory)).thenReturn(tsscStory);
			assertTrue(tsscStoryService.createStory(tsscStory, id).equals(tsscStory));
			verify(tsscStoryRepository, times(1)).save(tsscStory);
		} catch (StorySaveException | GameNotEsxistException e) {
			fail();

		}

	}

	/**
	 * Prueba que permite verificar, si al agregar un story con Valor de negocio,
	 * valor inicial de sprint y prioridad igual a cero y un game asociado que
	 * exista, no es posible la creación de un story
	 */
	@Test
	public void createStoryThrowStoryException() {
		tsscStory.setBusinessValue(BigDecimal.ZERO);
		tsscStory.setInitialSprint(BigDecimal.ZERO);
		tsscStory.setPriority(BigDecimal.ZERO);
		long id = 1;
		try {
			when(tsscGameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));
			tsscStoryService.createStory(tsscStory, id);

		} catch (StorySaveException | GameNotEsxistException e) {
			assertTrue(true);

		}
		verify(tsscStoryRepository, times(0)).save(tsscStory);

	}

	/**
	 * Prueba que permite verificar, si al agregar un story con Valor de negocio
	 * mayor que cero, valor inicial de sprint y prioridad igual a cero y un game
	 * asociado que exista, no es posible la creación de un story
	 */
	@Test
	public void createStoryThrowStoryException2() {
		tsscStory.setBusinessValue(BigDecimal.TEN);
		tsscStory.setInitialSprint(BigDecimal.ZERO);
		tsscStory.setPriority(BigDecimal.ZERO);
		long id = 1;
		try {
			when(tsscGameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));
			tsscStoryService.createStory(tsscStory, id);

		} catch (StorySaveException | GameNotEsxistException e) {
			assertTrue(true);

		}
		verify(tsscStoryRepository, times(0)).save(tsscStory);

	}

	/**
	 * Prueba que permite verificar, si al agregar un story con Valor de negocio,
	 * valor inicial de sprint mayor que cero y prioridad igual a cero y un game
	 * asociado que exista, no es posible la creación de un story
	 */
	@Test
	public void createStoryThrowStoryException3() {
		tsscStory.setBusinessValue(BigDecimal.TEN);
		tsscStory.setInitialSprint(BigDecimal.TEN);
		tsscStory.setPriority(BigDecimal.ZERO);
		long id = 1;
		try {
			when(tsscGameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));
			tsscStoryService.createStory(tsscStory, id);

		} catch (StorySaveException | GameNotEsxistException e) {
			assertTrue(true);

		}
		verify(tsscStoryRepository, times(0)).save(tsscStory);

	}

	/**
	 * Prueba que permite verificar, si al agregar un story con Valor de negocio,
	 * valor inicial de sprint y prioridad menor a cero y con un game asociado que
	 * exista, es posible la creación de un story
	 */
	@Test
	public void createStoryThrowStoryException4() {

		tsscStory.setBusinessValue(new BigDecimal(-99));
		tsscStory.setInitialSprint(new BigDecimal(-99));
		tsscStory.setPriority(new BigDecimal(-99));
		long id = 1;
		try {
			when(tsscGameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));
			tsscStoryService.createStory(tsscStory, id);
		} catch (StorySaveException | GameNotEsxistException e) {
			assertTrue(true);
		}
		verify(tsscStoryRepository, times(0)).save(tsscStory);

	}

	/**
	 * Prueba que permite verificar, si al agregar un story con Valor de negocio,
	 * valor inicial de sprint y prioridad mayor a cero y con un game asociado que
	 * no exista, no es posible la creación de un story
	 */
	@Test
	public void createStoryGameNullThrowStoryException() {

		tsscStory.setBusinessValue(new BigDecimal(99));
		tsscStory.setInitialSprint(new BigDecimal(99));
		tsscStory.setPriority(new BigDecimal(99));
		long id = 1;

		when(tsscGameRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(GameNotEsxistException.class, () -> {
			tsscStoryService.createStory(tsscStory, id);
		});

		verifyZeroInteractions(tsscStoryRepository);

	}

	// Test Update
	/**
	 * Prueba para verificar que con con Valor de negocio, valor inicial de sprint y
	 * prioridad mayor que cero y que la historia exista lo actualiza correctamente
	 */
	@Test
	public void updatecreateStoryNoThrowException() {
		tsscStory.setBusinessValue(BigDecimal.ONE);
		tsscStory.setInitialSprint(BigDecimal.ONE);
		tsscStory.setPriority(BigDecimal.ONE);

		try {
			Optional<TsscStory> optional = Optional.of(tsscStory);
			when(tsscStoryRepository.save(Mockito.any())).thenReturn(tsscStory);
			when(tsscStoryRepository.findById(tsscStory.getId())).thenReturn(optional);

			assertTrue(tsscStoryService.updateStory(tsscStory).equals(tsscStory));

		} catch (StorySaveException | StoryNotExistException e) {
			fail();

		}
		verify(tsscStoryRepository, times(1)).save(tsscStory);
	}

	/**
	 * Prueba para verificar que con un Valor de negocio, valor inicial de sprint y
	 * prioridad igual que cero y que la historia exista, lo no actualiza
	 * correctamente
	 */
	@Test
	public void updatecreateStoryThrowException() {
		tsscStory.setBusinessValue(BigDecimal.ZERO);
		tsscStory.setInitialSprint(BigDecimal.ZERO);
		tsscStory.setPriority(BigDecimal.ZERO);

		try {
			Optional<TsscStory> optional = Optional.of(tsscStory);

			when(tsscStoryRepository.save(Mockito.any())).thenReturn(tsscStory);
			when(tsscStoryRepository.findById(tsscStory.getId())).thenReturn(optional);

			tsscStoryService.updateStory(tsscStory);

		} catch (StorySaveException | StoryNotExistException e) {
			assertTrue(true);
		}
		verify(tsscStoryRepository, times(0)).save(tsscStory);
	}

	/**
	 * Permite verificar que no es posible actualizar una historia nula
	 */

	@Test
	public void updatecreateStoryNullThrowException() {

		try {
			tsscStoryService.updateStory(null).equals(tsscStory);

		} catch (StorySaveException | StoryNotExistException e) {
			assertTrue(true);
		}
		verify(tsscStoryRepository, times(0)).save(tsscStory);
	}

}
