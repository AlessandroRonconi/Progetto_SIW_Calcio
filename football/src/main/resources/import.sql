-- USERS
INSERT INTO users(id, name, surname, email, username) VALUES (nextval('users_seq'), 'Admin', 'Admin', 'admin@siwfootball.it', 'admin');

-- CREDENTIALS
INSERT INTO credentials (id, username, password, role, user_id) VALUES(nextval('credentials_seq'), 'admin', '$2a$12$bVR84ATc7PW6ZHkjuXdlme9UI7OyYCsFkFDvCbr7hedaoxiqIU6xe', 'ADMIN', 1);

-- TOURNAMENTS 
INSERT INTO torneo(id, name, year, description) VALUES (nextval('torneo_seq'), 'Under 21', 2026, 'Torneo amatoriale per gli under 21.');
INSERT INTO torneo(id, name, year, description) VALUES (nextval('torneo_seq'), 'Under 21', 2025, 'Torneo amatoriale per gli under 21.');
INSERT INTO torneo(id, name, year, description) VALUES (nextval('torneo_seq'), 'Under 18', 2026, 'Torneo amatoriale per gli under 18.');

-- TEAMS 
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'Roma Nord FC', 'Roma',    2005);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'Lazio Sud SC', 'Roma',    2008);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'Milano United', 'Milano',  2003);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'Torino Academy', 'Torino',  2010);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'Napoli Young', 'Napoli',  2007);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'Fiorentina Boys', 'Firenze', 2006);

-- TOURNAMENT-TEAM (torneo Under 21 2026, id=1) 
INSERT INTO torneo_squadre(torneo_id, squadre_id) VALUES (1, 1);
INSERT INTO torneo_squadre(torneo_id, squadre_id) VALUES (1, 51);
INSERT INTO torneo_squadre(torneo_id, squadre_id) VALUES (1, 101);
INSERT INTO torneo_squadre(torneo_id, squadre_id) VALUES (1, 151);
INSERT INTO torneo_squadre(torneo_id, squadre_id) VALUES (1, 201);
INSERT INTO torneo_squadre(torneo_id, squadre_id) VALUES (1, 251);

-- GAMES (torneo_id=1) 
-- Giornata 1 – tutte PLAYED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-07 15:00:00', 'Stadio Olimpico, Roma', 2, 1, 'PLAYED', 1, 1, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-07 17:30:00', 'Arena Milano, Milano', 1, 1, 'PLAYED', 1, 51, 151);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-07 20:00:00', 'Stadio Maradona, Napoli', 0, 3, 'PLAYED', 1, 201, 251);

-- Giornata 2 – tutte PLAYED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-14 15:00:00', 'Arena Milano, Milano', 2, 0, 'PLAYED', 1, 101, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-14 17:30:00', 'Stadio Franchi, Firenze', 1, 2, 'PLAYED', 1, 251, 151);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-14 20:00:00', 'Stadio Olimpico, Roma', 0, 0, 'PLAYED', 1, 51, 201);

-- Giornata 3 – una LIVE, due SCHEDULED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-21 15:00:00', 'Stadio Olimpico, Torino', 1, 0, 'LIVE', 1, 151, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-21 17:30:00', 'Stadio Maradona, Napoli', NULL, NULL, 'SCHEDULED', 1, 201, 101);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-21 20:00:00', 'Stadio Franchi, Firenze', NULL, NULL, 'SCHEDULED', 1, 251, 51);

-- Giornata 4 – tutte SCHEDULED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-28 15:00:00', 'Stadio Olimpico, Roma', NULL, NULL, 'SCHEDULED', 1, 1, 201);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-28 17:30:00', 'Arena Milano, Milano', NULL, NULL, 'SCHEDULED', 1, 101, 251);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id) VALUES (nextval('partita_seq'), '2026-03-28 20:00:00', 'Stadio Olimpico, Torino', NULL, NULL, 'SCHEDULED', 1, 151, 51);

-- GIOCATORI
-- Roma Nord FC (squadra_id = 1)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Luca',      'Ferretti',   '2005-03-12', 'Portiere',   1.88, 1);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Marco',     'Rinaldi',    '2006-07-22', 'Difensore',  1.82, 1);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Davide',    'Testa',      '2005-11-05', 'Difensore',  1.80, 1);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Andrea',    'Colombo',    '2007-01-18', 'Centrocampista', 1.76, 1);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Matteo',    'Gallo',      '2006-09-30', 'Attaccante', 1.79, 1);

-- Lazio Sud SC (squadra_id = 51)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Simone',    'Caruso',     '2005-05-14', 'Portiere',   1.90, 51);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Gabriele',  'Marini',     '2006-02-27', 'Difensore',  1.83, 51);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Federico',  'Esposito',   '2007-08-09', 'Difensore',  1.78, 51);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Lorenzo',   'De Luca',    '2005-12-03', 'Centrocampista', 1.75, 51);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Alessandro','Romano',     '2006-06-21', 'Attaccante', 1.81, 51);

-- Milano United (squadra_id = 101)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Pietro',    'Fontana',    '2005-04-17', 'Portiere',   1.86, 101);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Riccardo',  'Conti',      '2006-10-08', 'Difensore',  1.84, 101);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Nicola',    'Mancini',    '2007-03-25', 'Difensore',  1.79, 101);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Tommaso',   'Vitale',     '2005-07-11', 'Centrocampista', 1.77, 101);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Filippo',   'Serra',      '2006-01-29', 'Attaccante', 1.80, 101);

-- Torino Academy (squadra_id = 151)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Enrico',    'Greco',      '2005-09-06', 'Portiere',   1.87, 151);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Stefano',   'Moretti',    '2006-04-14', 'Difensore',  1.81, 151);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Giorgio',   'Lombardi',   '2007-06-02', 'Difensore',  1.83, 151);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Claudio',   'Ferrari',    '2005-02-19', 'Centrocampista', 1.74, 151);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Antonio',   'Barbieri',   '2006-11-07', 'Attaccante', 1.78, 151);

-- Napoli Young (squadra_id = 201)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Vincenzo',  'Amato',      '2005-08-23', 'Portiere',   1.89, 201);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Salvatore', 'Russo',      '2006-03-31', 'Difensore',  1.80, 201);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Pasquale',  'Giordano',   '2007-09-15', 'Difensore',  1.77, 201);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Ciro',      'De Santis',  '2005-05-28', 'Centrocampista', 1.76, 201);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Luigi',     'Sorrentino', '2006-12-10', 'Attaccante', 1.82, 201);

-- Fiorentina Boys (squadra_id = 251)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Massimo',   'Innocenti',  '2005-01-07', 'Portiere',   1.85, 251);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Roberto',   'Pellegrini', '2006-08-16', 'Difensore',  1.82, 251);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Bruno',     'Cattaneo',   '2007-04-22', 'Difensore',  1.79, 251);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Dario',     'Rossi',      '2005-10-13', 'Centrocampista', 1.73, 251);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Carlo',     'Benedetti',  '2006-05-04', 'Attaccante', 1.80, 251);