package br.com.compasso.votacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.repository.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	public Schedule getOne(Long id) {
		Optional<Schedule> schedule = scheduleRepository.findById(id);
		if (schedule.isPresent()) {
			return schedule.get();
		}
		return null;
	}

	public List<Schedule> getAll() {
		return scheduleRepository.findAll();
	}
	
	public void save(Schedule schedule) {
		scheduleRepository.save(schedule);
	}

}
