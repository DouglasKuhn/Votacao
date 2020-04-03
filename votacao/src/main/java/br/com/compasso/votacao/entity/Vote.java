package br.com.compasso.votacao.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.compasso.votacao.enumeration.EnumVote;

@Entity
public class Vote {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime createdAt = LocalDateTime.now();
	@OneToOne
	private User associate;
	@ManyToOne
	private Session session;
	@Enumerated(EnumType.STRING)
	private EnumVote vote;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public User getAssociate() {
		return associate;
	}
	
	public void setAssociate(User associate) {
		this.associate = associate;
	}
	
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public EnumVote getVote() {
		return vote;
	}
	
	public void setVote(EnumVote vote) {
		this.vote = vote;
	}
	
	

}
