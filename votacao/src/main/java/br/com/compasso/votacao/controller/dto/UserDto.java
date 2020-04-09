package br.com.compasso.votacao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.votacao.entity.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public static List<UserDto> converter(List<User> users) {
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}

}
