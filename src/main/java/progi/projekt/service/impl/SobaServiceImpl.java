package progi.projekt.service.impl;

import org.springframework.stereotype.Service;
import progi.projekt.model.Soba;
import progi.projekt.repository.SobaRepository;
import progi.projekt.service.SobaService;

import java.util.UUID;

@Service
public class SobaServiceImpl implements SobaService {

	private SobaRepository sobaRepository;

	public SobaServiceImpl(SobaRepository sobaRepository) {
		this.sobaRepository = sobaRepository;
	}

	@Override
	public Soba getByStudentId(UUID id) {
		//todo
		return new Soba();
	}

	@Override
	public Soba getById(UUID id) {
		return sobaRepository.findById(id).get();
	}
}