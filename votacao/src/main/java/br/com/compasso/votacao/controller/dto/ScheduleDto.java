package br.com.compasso.votacao.controller.dto;

import org.springframework.data.domain.Page;

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

	public static Page<ScheduleDto> converter(Page<Schedule> schedule) {
		return schedule.map(ScheduleDto::new);
	}

}
