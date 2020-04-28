package br.com.compasso.votacao.converter;

import br.com.compasso.votacao.controller.dto.ScheduleDto;
import br.com.compasso.votacao.controller.dto.SessionDto;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverter {

    public Page<ScheduleDto> scheduleListToListScheduleDto(Page<Schedule> schedules) {
        return schedules.map(ScheduleDto::new);
    }

}
