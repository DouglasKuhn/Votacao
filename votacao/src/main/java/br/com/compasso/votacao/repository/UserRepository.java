package br.com.compasso.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
