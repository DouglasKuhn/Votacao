package br.com.compasso.votacao.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.DetailedScheduleDto;
import br.com.compasso.votacao.controller.dto.ScheduleDto;
import br.com.compasso.votacao.controller.dto.SessionDto;
import br.com.compasso.votacao.controller.form.ScheduleForm;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.service.ScheduleService;
import br.com.compasso.votacao.service.SessionService;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private SessionService sessionService;
	
	@GetMapping
	public List<ScheduleDto> list() {
		List<Schedule> schedules = scheduleService.getAll();
		return ScheduleDto.converter(schedules);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ScheduleDto> register(@RequestBody @Valid ScheduleForm form, UriComponentsBuilder uriBuilder) {
		Schedule schedule = scheduleService.createSchedule(form);
		scheduleService.save(schedule);
		scheduleService.createSession(schedule, form.getTimeInMinutes());
		
		
		URI uri = uriBuilder.path("/schedule/{id}").buildAndExpand(schedule.getId()).toUri();
		return ResponseEntity.created(uri).body(new ScheduleDto(schedule));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailedScheduleDto> detail(@PathVariable Long id) {
		Schedule schedule = scheduleService.getOne(id);
		if (schedule != null) {
			return ResponseEntity.ok(new DetailedScheduleDto(schedule));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/sessions")
	public List<SessionDto> essionList() {
		List<Session> schedules = sessionService.findAll();
		return SessionDto.converter(schedules);
	}
}