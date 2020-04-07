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
import br.com.compasso.votacao.controller.form.ScheduleForm;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.repository.ScheduleRepository;
import br.com.compasso.votacao.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private ScheduleService service;
	
	@GetMapping
	public List<ScheduleDto> list() {
		List<Schedule> schedules = scheduleRepository.findAll();
		return ScheduleDto.converter(schedules);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ScheduleDto> register(@RequestBody @Valid ScheduleForm form, UriComponentsBuilder uriBuilder) {
		Schedule schedule = form.converter();
		scheduleRepository.save(schedule);
		
		URI uri = uriBuilder.path("/schedule/{id}").buildAndExpand(schedule.getId()).toUri();
		return ResponseEntity.created(uri).body(new ScheduleDto(schedule));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailedScheduleDto> detail(@PathVariable Long id) {
		Schedule schedule = service.getOne(id);
		if (schedule != null) {
			return ResponseEntity.ok(new DetailedScheduleDto(schedule));
		}
		return ResponseEntity.notFound().build();
	}
}