package br.com.compasso.votacao.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.votacao.entity.Session;

public class SessionDto {
	
	private Long id;
	private String title;
	private String description;
	private String status;
	private LocalDateTime begin = LocalDateTime.now();

	public SessionDto(Session session) {
		this.id = session.getId();
		this.title = session.getSchedule().getTitle();
		this.description = session.getSchedule().getDescription();
		this.status = session.getStatus().name();
		this.begin = session.getBegin();
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

	public LocalDateTime getBegin() {
		return begin;
	}

	public static List<SessionDto> converter(List<Session> session) {
		return session.stream().map(SessionDto::new).collect(Collectors.toList());
	}

}
