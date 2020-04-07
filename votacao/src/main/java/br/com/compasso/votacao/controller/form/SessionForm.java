package br.com.compasso.votacao.controller.form;

import com.sun.istack.NotNull;

public class SessionForm {
	
	@NotNull
	private Long scheduleId;
	private int timeInMinutes;
	
	public Long getScheduleId() {
		return scheduleId;
	}
	
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
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
