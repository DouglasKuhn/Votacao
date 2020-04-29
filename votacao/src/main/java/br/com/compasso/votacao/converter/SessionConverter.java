package br.com.compasso.votacao.converter;

import br.com.compasso.votacao.controller.dto.SessionDto;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class SessionConverter {


    public Page<SessionDto> sessionToSessionDto(Page<Session> sessions) {
        return sessions.map(SessionDto::new);
    }

    public Session scheduleToSession(Schedule schedule, int timeInMinutes) {
        return new Session(schedule, timeInMinutes);
    }
}
