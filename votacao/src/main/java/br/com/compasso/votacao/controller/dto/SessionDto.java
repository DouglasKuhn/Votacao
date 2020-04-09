package br.com.compasso.votacao.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.votacao.entity.Session;

public class SessionDto {

	private Long id;
	private Long scheduleId;
	private int timeInMinutes;
	private LocalDateTime begin;
	private LocalDateTime end;
	private String status;

	public SessionDto(Session session) {
		this.id = session.getId();
		this.scheduleId = session.getSchedule().getId();
		this.timeInMinutes = session.getTime();
		this.begin = session.getBegin();
		this.end = session.getEnd();
		this.status = session.getStatus().name();
	}

	public Long getId() {
		return id;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public int getTimeInMinutes() {
		return timeInMinutes;
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public String getStatus() {
		return status;
	}

	public static List<SessionDto> converter(List<Session> session) {
		return session.stream().map(SessionDto::new).collect(Collectors.toList());
	}

}
