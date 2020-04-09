package br.com.compasso.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
