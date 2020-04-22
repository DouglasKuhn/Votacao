package br.com.compasso.votacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import Utils.SessionService2;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.repository.SessionRepository;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceTest {
	
	@InjectMocks
	private SessionService2 sessionService2;
	
	@Mock
	private SessionRepository sessionRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllSessions() {
		Session session = new Session();
		session.setId(Long.parseLong("1"));
		
		List<Session> simulated = Arrays.asList(session, session, session, session, session);
		
		when(sessionRepository.findAll()).thenReturn(simulated);
		
		List<Session> realList = sessionService2.getAll();
		
		assertEquals(simulated, realList);
	}
}
