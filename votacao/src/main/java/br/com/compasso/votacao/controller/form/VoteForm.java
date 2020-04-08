package br.com.compasso.votacao.controller.form;

import com.sun.istack.NotNull;

import br.com.compasso.votacao.enumeration.EnumVote;

public class VoteForm {
	@NotNull
	private Long userId;
	@NotNull
	private EnumVote vote;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public EnumVote getVote() {
		return vote;
	}

	public void setVote(EnumVote vote) {
		this.vote = vote;
	}
	
	
	

}
