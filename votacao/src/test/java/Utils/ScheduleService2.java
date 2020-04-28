package Utils;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.repository.ScheduleRepository;

@Service
public class ScheduleService2 {

	private final ScheduleRepository scheduleRepository;
	
	public ScheduleService2(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	public List<Schedule> getAll() {
		return scheduleRepository.findAll();
	}
}
