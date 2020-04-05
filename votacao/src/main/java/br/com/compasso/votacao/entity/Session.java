package br.com.compasso.votacao.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.compasso.votacao.enumeration.EnumSessionStatus;
import br.com.compasso.votacao.enumeration.EnumVote;

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
	private EnumSessionStatus status = EnumSessionStatus.ABERTA;
	private Integer time;
	@OneToMany
	private List<Vote> votes;
	
	
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
	
	public Integer getTime() {
		return time;
	}
	
	public void setTime(Integer time) {
		this.time = time;
	}
	
	public List<Vote> getVotes() {
		return votes;
	}
	
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
	
	

}
