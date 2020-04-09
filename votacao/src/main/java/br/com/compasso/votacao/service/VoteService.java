package br.com.compasso.votacao.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.enumeration.EnumSessionStatus;
import br.com.compasso.votacao.repository.VoteRepository;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private UserService userService;

	public Vote createVote(@Valid VoteForm form, Long scheduleId) {
		return new Vote(userService.getOne(form.getUserId()), form.getVote(),
				sessionService.findByScheduleId(scheduleId));
	}

	public void save(Vote vote) {
		voteRepository.save(vote);
		
	}

	public boolean checkIfSessionIsOpen(Vote vote) {
		if (vote.getSession().getStatus().equals(EnumSessionStatus.VOTING)) {
			return true;
		}
		return false;
	}

	public boolean voteValidate(Vote vote) {
		if (checkIfSessionIsOpen(vote) && sessionService.voteSaveValidate(vote)) {
			return true;
		}
		return false;
	}

}
