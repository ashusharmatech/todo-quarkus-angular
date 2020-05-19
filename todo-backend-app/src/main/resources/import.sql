
INSERT INTO user(id, username,password,firstName,lastName) VALUES (nextval('hibernate_sequence'),'ashu','ashutosh','Ashutosh','Sharma');
INSERT INTO todo(id, title, completed) VALUES (nextval('hibernate_sequence'), 'Cherry', false);
INSERT INTO todo(id, title, completed) VALUES (nextval('hibernate_sequence'), 'Apple', false);
INSERT INTO todo(id, title, completed) VALUES (nextval('hibernate_sequence'), 'Banana', false);