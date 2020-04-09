package br.com.compasso.votacao.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.compasso.votacao.enumeration.EnumSessionStatus;

@Entity
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime begin = LocalDateTime.now();
	private LocalDateTime end;
	@Enumerated(EnumType.STRING)
	private EnumSessionStatus status = EnumSessionStatus.VOTING;
	@OneToOne
	private Schedule schedule;
	private int time;
	@OneToMany
	private Set<Vote> votes = new HashSet<Vote>();

	public Session() {}

	public Session(Schedule schedule, int timeInMinutes) {
		this.schedule = schedule;
		this.time = timeInMinutes;
		this.end = begin.plusMinutes(timeInMinutes);
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

	public EnumSessionStatus getStatus() {
		return status;
	}

	public void setStatus(EnumSessionStatus status) {
		this.status = status;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

}
