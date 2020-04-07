package br.com.compasso.votacao.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.SessionDto;
import br.com.compasso.votacao.controller.dto.VoteDto;
import br.com.compasso.votacao.controller.form.VoteForm;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.service.VoteService;

public class VoteController {
	
	@Autowired
	private VoteService voteService;

	@PostMapping
	@Transactional
	public ResponseEntity<VoteDto> vote(@RequestBody @Valid VoteForm form, UriComponentsBuilder uriBuilder) {
		Vote vote = voteService.criaSession(form);
		voteService.save(vote);
		
		URI uri = uriBuilder.path("/session/{id}").buildAndExpand(vote.getId()).toUri();
		return ResponseEntity.created(uri).body(new VoteDto(vote));
	}
}
