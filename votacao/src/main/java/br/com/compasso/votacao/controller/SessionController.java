package br.com.compasso.votacao.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.DetailedSessionDto;
import br.com.compasso.votacao.controller.dto.SessionDto;
import br.com.compasso.votacao.controller.form.SessionForm;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.service.SessionService;

//@RestController
//@RequestMapping("/session")
public class SessionController {

	@Autowired
	private SessionService sessionService;

	@GetMapping
	public List<SessionDto> list() {
		List<Session> schedules = sessionService.findAll();
		return SessionDto.converter(schedules);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<SessionDto> register(@RequestBody @Valid SessionForm form, UriComponentsBuilder uriBuilder) {
		Session session = sessionService.createSession(form);
		sessionService.save(session);

		URI uri = uriBuilder.path("/session/{id}").buildAndExpand(session.getId()).toUri();
		return ResponseEntity.created(uri).body(new SessionDto(session));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailedSessionDto> detail(@PathVariable Long id) {
		Session session = sessionService.getOne(id);
		if (session != null) {
			return ResponseEntity.ok(new DetailedSessionDto(session));
		}
		return ResponseEntity.notFound().build();
	}

}
