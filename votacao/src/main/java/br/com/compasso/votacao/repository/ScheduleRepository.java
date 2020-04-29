package br.com.compasso.votacao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.votacao.entity.Schedule;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	Page<Schedule> findByTitle(String scheduleTitle, Pageable pageable);

}
