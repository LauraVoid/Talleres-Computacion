package co.edu.icesi.fi.tics.tssc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.modelo.TsscSprint;
import co.edu.icesi.fi.tics.tssc.repositories.TsscSprintRepository;

@Service
public class TsscSprintServiceImp implements TsscSprintService {

	public TsscSprintRepository tsscSprintRepository;

	@Autowired
	public TsscSprintServiceImp(TsscSprintRepository tsscRepo) {
		tsscSprintRepository = tsscRepo;
	}

	@Override
	public TsscSprint createStory(TsscSprint story) {
		TsscSprint newSprint = tsscSprintRepository.save(story);

		return newSprint;
	}

	@Override
	public TsscSprint getTsscSprint(long id) {
		TsscSprint found = tsscSprintRepository.findById(id).get();
		return found;
	}

	@Override
	public void delete(TsscSprint story) {

		tsscSprintRepository.delete(story);

	}

}
