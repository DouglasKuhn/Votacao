package Utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.repository.ScheduleRepository;

@Service
public class ScheduleService2 {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public List<Schedule> getAll() {
		return scheduleRepository.findAll();
	}
}
