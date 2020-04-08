package br.com.compasso.votacao.controller.dto;

import br.com.compasso.votacao.entity.Vote;

public class VoteDto {
	
	private Long userId;
	private Long sessionId;
	private String vote;
	
	public VoteDto(Vote vote) {
		this.userId = vote.getUser().getId();
		this.sessionId = vote.getSession().getId();
		this.vote = vote.getVote().name();
	}

	public Long getUserId() {
		return userId;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public String getVote() {
		return vote;
	}
	
}
