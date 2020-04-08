package br.com.compasso.votacao.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.votacao.entity.Schedule;

public class ScheduleForm {

	@NotNull @NotEmpty
	private String title;
	@NotNull @NotEmpty
	private String description;
	private int timeInMinutes;
	

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

	public int getTimeInMinutes() {
		if (timeInMinutes < 1) {
			return 1;
		}
		return timeInMinutes;
	}
	
	public void setTimeInMinutes(int timeInMinutes) {
		this.timeInMinutes = timeInMinutes;
	}
	
	public Schedule criaSchedule() {
		return new Schedule(title, description);
	}
	
	
}
