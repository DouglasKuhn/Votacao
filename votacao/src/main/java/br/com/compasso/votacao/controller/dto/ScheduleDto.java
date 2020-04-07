package br.com.compasso.votacao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.votacao.entity.Schedule;

public class ScheduleDto {
	private Long id;
	private String title;
	private String description;
	
	public ScheduleDto(Schedule schedule) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.description = schedule.getDescription();
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
	
	public static List<ScheduleDto> converter(List<Schedule> schedule) {
		return schedule.stream().map(ScheduleDto::new).collect(Collectors.toList());
	}
	
}
