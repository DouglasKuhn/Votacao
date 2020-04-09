package br.com.compasso.votacao.exceptions;

public class SessionFinishedException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SessionFinishedException(String message) {
		super(message);
	}
	
}
