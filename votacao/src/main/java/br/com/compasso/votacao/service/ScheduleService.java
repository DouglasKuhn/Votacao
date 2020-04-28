package br.com.compasso.votacao.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.form.ScheduleForm;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.enumeration.EnumStatusSchedule;
import br.com.compasso.votacao.repository.ScheduleRepository;

@Service
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;

	private final UserService userService;

	private final SessionService sessionService;
	
	public ScheduleService(ScheduleRepository scheduleRepository, UserService userService,
			SessionService sessionService) {
		this.scheduleRepository = scheduleRepository;
		this.userService = userService;
		this.sessionService = sessionService;
	}

	public Schedule getOne(Long id) {
		Optional<Schedule> schedule = scheduleRepository.findById(id);
		if (schedule.isPresent()) {
			return schedule.get();
		}
		return null;
	}

	public Page<Schedule> getAll(Pageable pageable) {
		return scheduleRepository.findAll(pageable);
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

	public Page<Schedule> findByTitle(String scheduleTitle, Pageable pageable) {
		return scheduleRepository.findByTitle(scheduleTitle, pageable);
	}

}
