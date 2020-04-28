package br.com.compasso.votacao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.compasso.votacao.service.SessionService;

@Configuration
@EnableScheduling
public class TimerCount {

	private final SessionService sessionService;

	public TimerCount(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	@Scheduled(fixedRate = 10000)
	private void schedule() {
		sessionService.checkForEndedSessions();
	}

}
