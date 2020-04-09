package br.com.compasso.votacao.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.VoteDto;
import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.exceptions.SessionFinishedException;
import br.com.compasso.votacao.service.VoteService;

@RestController
@RequestMapping("/schedules")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@PostMapping("/{id}/vote")
	@Transactional
	public ResponseEntity<VoteDto> vote(@RequestBody @Valid VoteForm form, UriComponentsBuilder uriBuilder,
			@PathVariable Long id) {
		Vote vote = voteService.createVote(form, id);
		if (voteService.voteValidate(vote)) {
			voteService.save(vote);

			URI uri = uriBuilder.path("/schedules/{id}/vote").buildAndExpand(vote.getId()).toUri();
			return ResponseEntity.created(uri).body(new VoteDto(vote));
		} else
			throw new SessionFinishedException("Session is already finished");
	}
}
