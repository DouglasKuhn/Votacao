package br.com.compasso.votacao.config;

public class FormSessionErrorDto {

	private String error;

	public FormSessionErrorDto(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
