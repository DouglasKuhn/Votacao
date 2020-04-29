package br.com.compasso.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.Vote;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
