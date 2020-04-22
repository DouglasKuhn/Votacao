package br.com.compasso.votacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.compasso.votacao.entity.User;
import br.com.compasso.votacao.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void getAllUsers() {
		User user = new User("Usuario1", "usuario1@email.com", "123456");
		user.setId(Long.parseLong("1"));
		
		List<User> simulated = Arrays.asList(user, user, user, user, user);
		
		when(userRepository.findAll()).thenReturn(simulated);
		
		List<User> realList = userService.getAll();
		
		assertEquals(simulated, realList);
	}
}
