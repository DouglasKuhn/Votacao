package br.com.compasso.votacao.controller;

import br.com.compasso.votacao.controller.dto.DetailedScheduleDto;
import br.com.compasso.votacao.controller.dto.ScheduleDto;
import br.com.compasso.votacao.controller.dto.SessionDto;
import br.com.compasso.votacao.controller.form.ScheduleForm;
import br.com.compasso.votacao.converter.ScheduleConverter;
import br.com.compasso.votacao.converter.SessionConverter;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.service.ScheduleService;
import br.com.compasso.votacao.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ScheduleConverter scheduleConverter;

    @Autowired
    private SessionConverter sessionConverter;

    @GetMapping
    @Cacheable(value = "scheduleList")
    public Page<ScheduleDto> list(@RequestParam(required = false) Optional<String> scheduleTitle,
                                  @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {

        //TODO remover comentarios e falar sobre a adicao do optional
        if (scheduleTitle.isEmpty()) {
            Page<Schedule> schedules = scheduleService.getAll(pageable);
            return scheduleConverter.scheduleListToListScheduleDto(schedules);
//			return ScheduleDto.converter(schedules);
        } else {
            Page<Schedule> schedules = scheduleService.findByTitle(scheduleTitle.get(), pageable);
            return scheduleConverter.scheduleListToListScheduleDto(schedules);
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "scheduleList", allEntries = true)
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
        Page<Session> sessions = sessionService.findAll(pageable);
        return sessionConverter.sessionToSessionDto(sessions);
    }
}