package br.com.compasso.votacao.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.form.ScheduleForm;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.enumeration.EnumStatusSchedule;
import br.com.compasso.votacao.repository.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private SessionService sessionService;

	public Schedule getOne(Long id) {
		Optional<Schedule> schedule = scheduleRepository.findById(id);
		if (schedule.isPresent()) {
			return schedule.get();
		}
		return null;
	}

	public List<Schedule> getAll() {
		return scheduleRepository.findAll();
	}

	public void save(Schedule schedule) {
		scheduleRepository.save(schedule);
	}

	public Schedule createSchedule(@Valid ScheduleForm form) {
		return new Schedule(userService.getOne(form.getUserId()), form.getTitle(), form.getDescription());
	}

	public void createSession(Schedule schedule, int timeInMinutes) {
		Session session = sessionService.createSession(schedule, timeInMinutes);
		sessionService.save(session);
	}

	public void closeSchedule(Session session) {
		save(getScheduleFinalStatus(session.getSchedule()));
	}

	public Schedule getScheduleFinalStatus(Schedule schedule) {
		if (schedule.getVotesYes() > schedule.getVotesNo())
			schedule.setStatus(EnumStatusSchedule.APROVED);
		else
			schedule.setStatus(EnumStatusSchedule.DENIED);
		return schedule;
	}

}
