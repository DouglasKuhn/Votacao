package br.com.compasso.votacao.exceptions;

public class UserNotPermitedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotPermitedException(String message) {
		super(message);
	}
}
