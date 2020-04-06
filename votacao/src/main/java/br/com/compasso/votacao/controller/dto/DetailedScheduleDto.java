package br.com.compasso.votacao.controller.dto;

import java.time.LocalDateTime;

import br.com.compasso.votacao.entity.Schedule;

public class DetailedScheduleDto {
	
	private Long id;
	private String title;
	private String description;
	private String status;
	private LocalDateTime createdAt;
	private String creator;
	private Integer votesYes;
	private Integer votesNo;
	
	public DetailedScheduleDto(Schedule schedule) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.description = schedule.getDescription();
		this.status = schedule.getStatus().toString();
		this.createdAt = schedule.getCreatedAt();
		this.creator = schedule.getCreator().getName();
		this.votesYes = schedule.getVotesYes();
		this.votesNo = schedule.getVotesNo();
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getCreator() {
		return creator;
	}

	public Integer getVotesYes() {
		return votesYes;
	}

	public Integer getVotesNo() {
		return votesNo;
	}
	
	
}
