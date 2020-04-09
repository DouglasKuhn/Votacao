package br.com.compasso.votacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.enumeration.EnumStatusSchedule;

@SpringBootTest
public class testScheduleService {
	
	public ScheduleService scheduleService;
	public SessionService sessionService;
	public UserService userService;
	public VoteService voteService;
	
	@Test
	public void scheduleStatusTest() {
		Schedule schedule = new Schedule();
		schedule.setVotesNo(3);
		schedule.setVotesYes(5);
		System.out.println(schedule.getStatus());
		
		Schedule schedule2 = new Schedule();
		schedule2 = scheduleService.getScheduleFinalStatus(schedule);
		System.out.println(schedule2.getStatus());
		assertEquals(EnumStatusSchedule.APROVED, schedule2.getStatus());
	}
	
	@Test
	public void test() {
		assertEquals(1, 1);
	}
}
