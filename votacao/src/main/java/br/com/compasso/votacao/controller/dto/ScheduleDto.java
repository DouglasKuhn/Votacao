package br.com.compasso.votacao.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.votacao.entity.Schedule;

public class ScheduleDto {
	private Long id;
	private String title;
	private String description;
	private String status;
	
	public ScheduleDto(Schedule schedule) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.description = schedule.getDescription();
		this.status = schedule.getStatus().toString();
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getStatus() {
		return status;
	}
	
	public static List<ScheduleDto> converter(List<Schedule> schedule) {
		return schedule.stream().map(ScheduleDto::new).collect(Collectors.toList());
	}
	
}
