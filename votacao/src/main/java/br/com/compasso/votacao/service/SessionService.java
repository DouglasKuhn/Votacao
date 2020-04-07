package br.com.compasso.votacao.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.form.SessionForm;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.repository.SessionRepository;

@Service
public class SessionService {
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private ScheduleService scheduleService;

	public Session getOne(Long id) {
		Optional<Session> session = sessionRepository.findById(id);
		if (session.isPresent()) {
			return session.get();
		}
		return null;
	}

	public List<Session> findAll() {
		return sessionRepository.findAll();
	}

	public void save(Session session) {
		sessionRepository.save(session);
	}

	public Session criaSession(@Valid SessionForm form) {
		return new Session(scheduleService.getOne(form.getScheduleId()), form.getTimeInMinutes());
	}

}
