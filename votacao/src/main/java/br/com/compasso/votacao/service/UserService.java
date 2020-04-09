package br.com.compasso.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.User;
import br.com.compasso.votacao.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getOne(Long id) {
		return userRepository.getOne(id);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

}
