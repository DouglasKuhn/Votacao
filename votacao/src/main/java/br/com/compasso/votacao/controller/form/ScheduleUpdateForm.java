package br.com.compasso.votacao.controller.form;

import javax.validation.constraints.NotEmpty;

import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.repository.ScheduleRepository;

public class ScheduleUpdateForm {
	
	@NotEmpty
	private String title;
	@NotEmpty
	private String description;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Schedule update(Long id, ScheduleRepository scheduleRepository) {
		return null;
	}

}
