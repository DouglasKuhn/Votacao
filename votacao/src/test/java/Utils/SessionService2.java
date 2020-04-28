package Utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.repository.SessionRepository;

@Service
public class SessionService2 {
	
	private final SessionRepository sessionRepository;
	
	public SessionService2(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	public List<Session> getAll() {
		return sessionRepository.findAll();
	}

}
