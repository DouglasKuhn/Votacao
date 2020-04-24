package br.com.compasso.votacao.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ScheduleForm {

	@NotNull
	private Long userId;
	@NotNull
	@NotEmpty
	private String title;
	@NotNull
	@NotEmpty
	private String description;
	private int timeInMinutes;
	
	

	public ScheduleForm() {
	}

	public ScheduleForm(@NotNull Long userId, @NotNull @NotEmpty String title, @NotNull @NotEmpty String description,
			int timeInMinutes) {
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.timeInMinutes = timeInMinutes;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

}
