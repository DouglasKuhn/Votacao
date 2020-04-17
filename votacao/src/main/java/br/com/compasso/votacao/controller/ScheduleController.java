package br.com.compasso.votacao.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Page<ScheduleDto> list(@RequestParam(required = false) String scheduleTitle, 
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		
		if (scheduleTitle == null) {
			Page<Schedule> schedules = scheduleService.getAll(pageable);
			return ScheduleDto.converter(schedules);			
		} else {
			Page<Schedule> schedules = scheduleService.findByTitle(scheduleTitle, pageable);
			return ScheduleDto.converter(schedules);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ScheduleDto> register(@RequestBody @Valid ScheduleForm form,
			UriComponentsBuilder uriBuilder) {
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
	public Page<SessionDto> sessionList(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		Page<Session> schedules = sessionService.findAll(pageable);
		return SessionDto.converter(schedules);
	}
}