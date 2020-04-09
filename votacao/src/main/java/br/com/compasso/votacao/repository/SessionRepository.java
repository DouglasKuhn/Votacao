package br.com.compasso.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

	Session findByScheduleId(Long id);

}
