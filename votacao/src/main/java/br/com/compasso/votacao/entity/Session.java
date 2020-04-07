package br.com.compasso.votacao.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.compasso.votacao.enumeration.EnumSessionStatus;

@Entity
public class Session {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime begin = LocalDateTime.now();
	private LocalDateTime end;
	@OneToOne
	private Schedule schedule;
	@Enumerated(EnumType.STRING)
	private EnumSessionStatus status = EnumSessionStatus.VOTING;
	private int time;
	
	public Session() {}
	
	public Session(Schedule schedule, int time) {
		this.schedule = schedule;
		this.time = time;
		this.end = begin.plusMinutes(time);
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getBegin() {
		return begin;
	}
	
	public void setBegin(LocalDateTime begin) {
		this.begin = begin;
	}
	
	public LocalDateTime getEnd() {
		return end;
	}
	
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public EnumSessionStatus getStatus() {
		return status;
	}
	
	public void setStatus(EnumSessionStatus status) {
		this.status = status;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}

}
