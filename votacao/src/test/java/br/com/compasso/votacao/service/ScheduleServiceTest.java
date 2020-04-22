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

import Utils.ScheduleService2;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.User;
import br.com.compasso.votacao.enumeration.EnumStatusSchedule;
import br.com.compasso.votacao.repository.ScheduleRepository;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

	@InjectMocks
	private ScheduleService2 scheduleService2;
	
	@InjectMocks
	private ScheduleService scheduleService;
	
	@Mock
	private ScheduleRepository scheduleRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllSchecdules() {
		User user = new User("Usuario1", "usuario1@email.com", "123456");
		user.setId(Long.parseLong("1"));
		Schedule schedule = new Schedule(user, "Titulo da Schedule", "Descricao da Schedule");
		schedule.setVotesYes(2);
		schedule.setVotesNo(1);
		
		List<Schedule> simulated = Arrays.asList(schedule, schedule, schedule, schedule, schedule);
		
		when(scheduleRepository.findAll()).thenReturn(simulated);
		
		List<Schedule> realList = scheduleService2.getAll();
		
		assertEquals(simulated, realList);
	}
	
	@Test
	public void getScheduleFinalStatus() {
		User user = new User("Usuario1", "usuario1@email.com", "123456");
		user.setId(Long.parseLong("1"));
		
		Schedule scheduleYES = new Schedule(user, "Titulo da Schedule YES", "Descricao da Schedule YES");
		scheduleYES.setVotesYes(2);
		scheduleYES.setVotesNo(1);
		
		Schedule scheduleNO = new Schedule(user, "Titulo da Schedule NO", "Descricao da Schedule NO");
		scheduleNO.setVotesYes(1);
		scheduleNO.setVotesNo(2);
		
		Schedule simulated1 = scheduleYES;
		simulated1.setStatus(EnumStatusSchedule.APROVED);
		Schedule simulated2 = scheduleNO;
		simulated2.setStatus(EnumStatusSchedule.DENIED);
		
		Schedule real1 = scheduleService.getScheduleFinalStatus(scheduleYES);
		Schedule real2 = scheduleService.getScheduleFinalStatus(scheduleNO);
		
		assertEquals(simulated1, real1);
		assertEquals(simulated2, real2);
	}
}
