package br.com.compasso.votacao.controller.form;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.compasso.votacao.entity.User;

public class UserForm {

	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	@Length(min = 5)
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User criaUser() {
		return new User(name, email, password);
	}

}
