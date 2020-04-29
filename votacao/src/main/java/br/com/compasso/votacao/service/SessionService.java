package br.com.compasso.votacao.service;

import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.enumeration.EnumSessionStatus;
import br.com.compasso.votacao.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    //TODO revisar
    //@Autowired
    private final ScheduleService scheduleService;

    public SessionService(SessionRepository sessionRepository, ScheduleService scheduleService) {
        this.sessionRepository = sessionRepository;
        this.scheduleService = scheduleService;
    }

    public Session getOne(Long id) {
        Optional<Session> session = sessionRepository.findById(id);
        if (session.isPresent()) {
            return session.get();
        }
        return null;
    }

    public Page<Session> findAll(Pageable pageable) {
        return sessionRepository.findAll(pageable);
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
        sessionRepository.findAll().forEach(session -> {
            if (session.getStatus().equals(EnumSessionStatus.VOTING) && LocalDateTime.now().isAfter(session.getEnd())) {
                session.setStatus(EnumSessionStatus.FINISHED);
                save(session);
                scheduleService.closeSchedule(session);
            }
        });

    }

}
