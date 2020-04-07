package br.com.compasso.votacao.controller.dto;

import java.time.LocalDateTime;

import br.com.compasso.votacao.entity.Session;

public class DetailedSessionDto {

	private Long id;
	private String title;
	private String description;
	private String status;
	private Integer time;
	private LocalDateTime begin;
	private LocalDateTime end;
	
	public DetailedSessionDto(Session session) {
		super();
		this.id = session.getId();
		this.title = session.getSchedule().getTitle();
		this.description = session.getSchedule().getDescription();
		this.status = session.getStatus().name();
		this.time = session.getTime();
		this.begin = session.getBegin();
		this.end = session.getEnd();
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

	public Integer getTime() {
		return time;
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public LocalDateTime getEnd() {
		return end;
	}
	
}
