package br.com.compasso.votacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.repository.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;

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
	
	public Session findByScheduleId(Long scheduleId) {
		return sessionRepository.findByScheduleId(scheduleId);
	}
	
	public Session createSession(Schedule schedule, int timeInMinutes) {
		return new Session(schedule, timeInMinutes);
	}

	public boolean voteSaveValidate(Vote vote) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
