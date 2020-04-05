package br.com.compasso.votacao.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.votacao.controller.dto.ScheduleDto;
import br.com.compasso.votacao.entity.Schedule;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@GetMapping
	public List<ScheduleDto> lista() {
		Schedule schedule = new Schedule("titulo", "descricao");
		
		return ScheduleDto.converter(Arrays.asList(schedule, schedule, schedule));
	}
}
