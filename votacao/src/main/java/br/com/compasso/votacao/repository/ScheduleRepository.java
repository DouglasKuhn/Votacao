package br.com.compasso.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
