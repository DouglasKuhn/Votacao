package br.com.compasso.votacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.enumeration.EnumSessionStatus;
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

	public Session findByScheduleId(Long scheduleId) {
		return sessionRepository.findByScheduleId(scheduleId);
	}

	public Session createSession(Schedule schedule, int timeInMinutes) {
		return new Session(schedule, timeInMinutes);
	}

	public void checkForEndedSessions() {
		findAll().forEach(session -> {
			if (session.getStatus().equals(EnumSessionStatus.VOTING) && LocalDateTime.now().isAfter(session.getEnd())) {
				session.setStatus(EnumSessionStatus.FINISHED);
				save(session);
				scheduleService.closeSchedule(session);
			}
		});

	}

}
