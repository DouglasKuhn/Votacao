INSERT INTO USER(email, name, password) VALUES('	aluno1@email.com', 'Aurelio', '123456');
INSERT INTO USER(email, name, password) VALUES('	aluno2@email.com', 'Manuel', '654321');

INSERT INTO SCHEDULE(description, status, title, votes_no, votes_yes, creator_id) VALUES('pauta1', 'OK', 'top',	'4', '7', '1');
INSERT INTO SCHEDULE(description, status, title, votes_no, votes_yes, creator_id) VALUES('pauta2','DENIED', 'topzera',	'1', '9', '2');

INSERT INTO SESSION(status, time, schedule_id) VALUES('OPEN', '5', '1');
INSERT INTO SESSION(status, time, schedule_id) VALUES('FINISHED', '8', '2');

INSERT INTO VOTE(vote, associate_id, session_id) VALUES('YES', '1', '1');
INSERT INTO VOTE(vote, associate_id, session_id) VALUES('NO', '2', '2');

INSERT INTO SESSION_VOTES(session_id, votes_id) VALUES('1', '1');
INSERT INTO SESSION_VOTES(session_id, votes_id) VALUES('2', '2');