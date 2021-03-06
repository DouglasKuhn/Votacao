package br.com.compasso.votacao.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.compasso.votacao.exceptions.SessionFinishedException;
import br.com.compasso.votacao.exceptions.UserNotPermitedException;

@RestControllerAdvice
public class HandlerErrorValidation {

	private final MessageSource messageSource;

	public HandlerErrorValidation(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormErrorDto> handler(MethodArgumentNotValidException exception) {
		List<FormErrorDto> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			FormErrorDto error = new FormErrorDto(e.getField(), message);
			dto.add(error);
		});

		return dto;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public FormSessionErrorDto handler(IllegalArgumentException exception) {
		return new FormSessionErrorDto(exception.getLocalizedMessage());
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidFormatException.class)
	public FormSessionErrorDto handler(InvalidFormatException exception) {
		return new FormSessionErrorDto(exception.getOriginalMessage());
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ SessionFinishedException.class, UserNotPermitedException.class })
	public FormSessionErrorDto genericHandler(RuntimeException exception) {
		return new FormSessionErrorDto(exception.getMessage());
	}

}
