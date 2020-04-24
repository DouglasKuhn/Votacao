package br.com.compasso.votacao.controller;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.compasso.votacao.config.security.AuthenticationService;
import br.com.compasso.votacao.config.security.TokenService;
import br.com.compasso.votacao.controller.form.LoginForm;
import br.com.compasso.votacao.controller.form.ScheduleForm;
import br.com.compasso.votacao.entity.Schedule;
import br.com.compasso.votacao.entity.User;
import br.com.compasso.votacao.service.ScheduleService;
import br.com.compasso.votacao.service.SessionService;

@RunWith(SpringRunner.class)
@WebMvcTest(ScheduleController.class)
public class ScheduleControllerTest {


	@MockBean
	private ScheduleService scheduleService;

	@MockBean
	private SessionService sessionService;
	
	@MockBean
	private AuthenticationService authenticationService;
	
	@MockBean
	private TokenService tokenService;
	
	@MockBean
	private AuthenticationManager authManager;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() throws Exception {
		Gson gson = new Gson();
		
		ScheduleForm scheduleForm = new ScheduleForm(1l, "Titulo do schedule", "Descricao do schedule", 1);
		
		User user = new User("Aurelio", "pessoa1@email.com", "123456");
		Schedule schedule = new Schedule(user, "Titulo da schedule", "descricao da schedule");
		
		when(scheduleService.createSchedule(scheduleForm)).thenReturn(schedule);
		
		LoginForm loginForm = new LoginForm(user.getEmail(), user.getPassword());
		
		UsernamePasswordAuthenticationToken loginData = loginForm.converter();
		Authentication authentication = authManager.authenticate(loginData);
		String token = tokenService.generateToken(authentication);
		System.out.println("--->  " + token); //Aqui ta retornando null
		
		mockMvc.perform(MockMvcRequestBuilders.post("/schedules")
				.header("Bearer", token)
				.content(gson.toJson(scheduleForm))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
//	@Test
//	public void registerScheduleTest() throws Exception {
//		String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
//		
//		Gson gson = new Gson();
//		
//		ScheduleForm form = new ScheduleForm(1l, "Titulo do schedule", "Descricao do schedule", 1);
//		
//		User user = new User("Aurelio", "pessoa1@email.com", "123456");
//		Schedule schedule = new Schedule(user, "Titulo da schedule", "descricao da schedule");
//		
//		
//		when(scheduleService.createSchedule(form)).thenReturn(schedule);
//		
////		when(tokenService.)
//		
//		HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
//		CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
//
//
//		mockMvc.perform(MockMvcRequestBuilders
//				.post("/schedules")
//				.header("Bearer", "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJWb3RlIEFQSSIsInN1YiI6IjEiLCJpYXQiOjE1ODc1Nzg3MjUsImV4cCI6MTU4NzY2NTEyNX0.VzUgLW-HNckxObwRn24iKz-hdVLDH9t5LIXNbDMmleg")
//				.sessionAttr(TOKEN_ATTR_NAME, csrfToken)
//				.param(csrfToken.getParameterName(), csrfToken.getToken())
//				.content(gson.toJson(form))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				)
//				.andExpect(MockMvcResultMatchers.status().isCreated());
//	}
//
//	@Test
//	void createScheduleBadRequestTest() throws Exception {
//		Gson gson = new Gson();
//
//		User user = new User("Usuario1", "usuario1@email.com", "123456");
//		user.setId(Long.parseLong("1"));
//
//		var scheduleForm = new ScheduleForm();
//		scheduleForm.setTimeInMinutes(2);
//		scheduleForm.setTitle("");
//		scheduleForm.setDescription("Descricao do Schedule");
//		scheduleForm.setUserIdr(Long.parseLong("1"));
//
//		when(userRepository.getOne(any())).thenReturn(user);
//
//		doNothing().when(scheduleService).save(any(Schedule.class));
//		doNothing().when(scheduleService).createSession(any(Schedule.class), any());
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/schedules")
//				.content(gson.toJson(scheduleForm))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				)
//				.andExpect(MockMvcResultMatchers.status().isBadRequest());
//	}
//
//	@Test
//	void getAllSchedulesTest() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/schedules")
//				.accept(MediaType.APPLICATION_JSON)
//				)
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}
}
