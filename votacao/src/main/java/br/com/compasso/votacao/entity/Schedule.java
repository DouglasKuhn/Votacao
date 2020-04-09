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

import br.com.compasso.votacao.enumeration.EnumStatusSchedule;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	@Enumerated(EnumType.STRING)
	private EnumStatusSchedule status = EnumStatusSchedule.VOTING;
	private LocalDateTime createdAt = LocalDateTime.now();
	@OneToOne
	private User creator;
	private Integer votesYes = 0;
	private Integer votesNo = 0;
	
	public Schedule() {}

	public Schedule(User user, String title, String description) {
		this.creator = user;
		this.title = title;
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public EnumStatusSchedule getStatus() {
		return status;
	}
	
	public void setStatus(EnumStatusSchedule status) {
		this.status = status;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public User getCreator() {
		return creator;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public Integer getVotesYes() {
		return votesYes;
	}
	
	public void setVotesYes(Integer votesYes) {
		this.votesYes = votesYes;
	}
	
	public Integer getVotesNo() {
		return votesNo;
	}
	
	public void setVotesNo(Integer votesNo) {
		this.votesNo = votesNo;
	}
	
}
