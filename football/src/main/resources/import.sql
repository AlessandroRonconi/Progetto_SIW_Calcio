-- USERS
INSERT INTO users(id, name, surname, email, username) VALUES (nextval('users_seq'), 'Admin', 'Admin', 'admin@siwfootball.it', 'admin');
INSERT INTO users(id, name, surname, email, username) VALUES (nextval('users_seq'), 'Mario', 'Rossi', 'rossimario@gmail.com', 'marione');

-- CREDENTIALS
INSERT INTO credentials (id, username, password, role, user_id) VALUES(nextval('credentials_seq'), 'admin', '$2a$12$bVR84ATc7PW6ZHkjuXdlme9UI7OyYCsFkFDvCbr7hedaoxiqIU6xe', 'ADMIN', 1);
INSERT INTO credentials (id, username, password, role, user_id) VALUES(nextval('credentials_seq'), 'marione', '$2a$12$7JxLG.5bfqjTQbMLjmxtJOr091JzlSaHI9p0OoGlqoCoUWU8P2Dju', 'USER', 51);

-- TOURNAMENTS
INSERT INTO torneo(id, name, year, description) VALUES (nextval('torneo_seq'), 'Under 21 Roma 2025', 2025, 'Torneo amatoriale per gli under 21 di Roma.');
INSERT INTO torneo(id, name, year, description) VALUES (nextval('torneo_seq'), 'Under 21 Roma 2026', 2026, 'Torneo amatoriale per gli under 21 di Roma.');
INSERT INTO torneo(id, name, year, description) VALUES (nextval('torneo_seq'), 'Under 18 Lazio 2026', 2026, 'Torneo amatoriale per gli under 18 delle provincie del Lazio.');

-- TEAMS 
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'Ostia FC', 'Roma',    2005);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'ASD Ottavia', 'Roma',    2008);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'AS Primavalle', 'Roma',  2003);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'Giardinetti Calcio', 'Roma',  2010);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'Garbatella FC', 'Roma',  2007);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'ASD La Storta', 'Roma', 2006);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'ASD Tivoli Calcio', 'Tivoli', 2009);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'Pomezia Calcio', 'Pomezia', 2004);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'US Civitavecchia Giovanile', 'Civitavecchia', 2011);
INSERT INTO squadra(id, name, city, year_of_foundation) VALUES (nextval('squadra_seq'), 'ASD Frosinone Giovani', 'Frosinone', 2012);
SELECT setval('squadra_seq', 452);

-- TOURNAMENT-TEAM
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (1, 1);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (1, 51);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (1, 101);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (1, 151);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (1, 201);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (1, 251);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (51, 1);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (51, 51);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (51, 101);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (51, 151);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (51, 201);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (51, 251);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (101, 301);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (101, 351);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (101, 401);
INSERT INTO torneo_squadre(tornei_id, squadre_id) VALUES (101, 451);

-- ARBITRI
INSERT INTO arbitro(id, first_name, last_name, code) VALUES (nextval('arbitro_seq'), 'Gianluca', 'Rocchi', 10001);
INSERT INTO arbitro(id, first_name, last_name, code) VALUES (nextval('arbitro_seq'), 'Daniele', 'Orsato', 10002);
INSERT INTO arbitro(id, first_name, last_name, code) VALUES (nextval('arbitro_seq'), 'Paolo', 'Valeri', 10003);
INSERT INTO arbitro(id, first_name, last_name, code) VALUES (nextval('arbitro_seq'), 'Massimiliano', 'Irrati', 10004);
INSERT INTO arbitro(id, first_name, last_name, code) VALUES (nextval('arbitro_seq'), 'Marco', 'Guida', 10005);

-- GAMES (torneo_id=1)
-- Giornata 1 (2025-09-14)
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-09-14 15:00:00', 'Stadio Vittorio Bachelet, Ostia', 2, 1, 'PLAYED', 1, 1, 251, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-09-14 17:30:00', 'Stadio Ottavia, Roma', 1, 1, 'PLAYED', 1, 51, 201, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-09-14 20:00:00', 'Campo Sportivo Primavalle, Roma', 3, 0, 'PLAYED', 1, 101, 151, 101);

-- Giornata 2 (2025-09-21)
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-09-21 15:00:00', 'Stadio Vittorio Bachelet, Ostia', 1, 0, 'PLAYED', 1, 1, 201, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-09-21 17:30:00', 'Campo La Storta, Roma', 2, 2, 'PLAYED', 1, 251, 151, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-09-21 20:00:00', 'Stadio Ottavia, Roma', 0, 1, 'PLAYED', 1, 51, 101, 101);

-- Giornata 3 (2025-09-28)
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-09-28 15:00:00', 'Stadio Vittorio Bachelet, Ostia', 4, 1, 'PLAYED', 1, 1, 151, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-09-28 17:30:00', 'Stadio Garbatella, Roma', 2, 0, 'PLAYED', 1, 201, 101, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-09-28 20:00:00', 'Campo La Storta, Roma', 1, 1, 'PLAYED', 1, 251, 51, 101);

-- Giornata 4 (2025-10-05)
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-05 15:00:00', 'Stadio Vittorio Bachelet, Ostia', 3, 2, 'PLAYED', 1, 1, 101, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-05 17:30:00', 'Centro Sportivo Giardinetti, Roma', 0, 0, 'PLAYED', 1, 151, 51, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-05 20:00:00', 'Stadio Garbatella, Roma', 2, 1, 'PLAYED', 1, 201, 251, 101);

-- Giornata 5 (2025-10-12)
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-12 15:00:00', 'Stadio Vittorio Bachelet, Ostia', 1, 1, 'PLAYED', 1, 1, 51, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-12 17:30:00', 'Campo Sportivo Primavalle, Roma', 2, 0, 'PLAYED', 1, 101, 251, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-12 20:00:00', 'Centro Sportivo Giardinetti, Roma', 1, 2, 'PLAYED', 1, 151, 201, 101);

-- Giornata 6 (2025-10-19) - ritorno
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-19 15:00:00', 'Campo La Storta, Roma', 0, 3, 'PLAYED', 1, 251, 1, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-19 17:30:00', 'Stadio Garbatella, Roma', 1, 0, 'PLAYED', 1, 201, 51, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-19 20:00:00', 'Centro Sportivo Giardinetti, Roma', 2, 2, 'PLAYED', 1, 151, 101, 101);

-- Giornata 7 (2025-10-26)
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-26 15:00:00', 'Stadio Garbatella, Roma', 0, 2, 'PLAYED', 1, 201, 1, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-26 17:30:00', 'Centro Sportivo Giardinetti, Roma', 1, 1, 'PLAYED', 1, 151, 251, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-10-26 20:00:00', 'Campo Sportivo Primavalle, Roma', 2, 1, 'PLAYED', 1, 101, 51, 101);

-- Giornata 8 (2025-11-02)
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-11-02 15:00:00', 'Centro Sportivo Giardinetti, Roma', 1, 1, 'PLAYED', 1, 151, 1, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-11-02 17:30:00', 'Campo Sportivo Primavalle, Roma', 3, 1, 'PLAYED', 1, 101, 201, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-11-02 20:00:00', 'Stadio Ottavia, Roma', 2, 0, 'PLAYED', 1, 51, 251, 101);

-- Giornata 9 (2025-11-09)
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-11-09 15:00:00', 'Campo Sportivo Primavalle, Roma', 0, 1, 'PLAYED', 1, 101, 1, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-11-09 17:30:00', 'Stadio Ottavia, Roma', 1, 2, 'PLAYED', 1, 51, 151, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-11-09 20:00:00', 'Campo La Storta, Roma', 3, 2, 'PLAYED', 1, 251, 201, 101);

-- Giornata 10 (2025-11-16) - ultima di ritorno
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-11-16 15:00:00', 'Stadio Ottavia, Roma', 2, 2, 'PLAYED', 1, 51, 1, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-11-16 17:30:00', 'Campo La Storta, Roma', 1, 0, 'PLAYED', 1, 251, 101, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2025-11-16 20:00:00', 'Stadio Garbatella, Roma', 0, 1, 'PLAYED', 1, 201, 151, 101);

-- GAMES (torneo_id=51) 
-- Giornata 1 – tutte PLAYED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-07 15:00:00', 'Stadio Vittorio Bachelet, Ostia', 2, 1, 'PLAYED', 51, 1, 51, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-07 17:30:00', 'Stadio Ottavia, Roma', 1, 1, 'PLAYED', 51, 51, 151, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-07 20:00:00', 'Stadio Garbatella, Roma', 0, 3, 'PLAYED', 51, 201, 251, 101);

-- Giornata 2 – tutte PLAYED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-14 15:00:00', 'Campo Sportivo Primavalle, Roma', 2, 0, 'PLAYED', 51, 101, 1, 51);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-14 17:30:00', 'Campo La Storta, Roma', 1, 2, 'PLAYED', 51, 251, 151, 101);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-14 20:00:00', 'Stadio Ottavia, Roma', 0, 0, 'PLAYED', 51, 51, 201, 1);

-- Giornata 3 – una LIVE, due SCHEDULED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-21 15:00:00', 'Centro Sportivo Giardinetti, Roma', 1, 0, 'LIVE', 51, 151, 1, 101);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-21 17:30:00', 'Stadio Garbatella, Roma', NULL, NULL, 'SCHEDULED', 51, 201, 101, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-21 20:00:00', 'Campo La Storta, Roma', NULL, NULL, 'SCHEDULED', 51, 251, 51, 51);

-- Giornata 4 – tutte SCHEDULED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-28 15:00:00', 'Stadio Vittorio Bachelet, Ostia', NULL, NULL, 'SCHEDULED', 51, 1, 201, 1);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-28 17:30:00', 'Campo Sportivo Primavalle, Roma', NULL, NULL, 'SCHEDULED', 51, 101, 251, 101);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-03-28 20:00:00', 'Centro Sportivo Giardinetti, Roma', NULL, NULL, 'SCHEDULED', 51, 151, 51, 51);

-- GAMES (torneo_id=101)
-- Giornata 1 - PLAYED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-04-11 15:00:00', 'Stadio Comunale, Tivoli', 2, 2, 'PLAYED', 101, 301, 351, 151);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-04-11 17:30:00', 'Stadio Miralanza, Civitavecchia', 1, 0, 'PLAYED', 101, 401, 451, 201);

-- Giornata 2 - una LIVE
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-04-18 15:00:00', 'Stadio Comunale, Tivoli', 1, 1, 'LIVE', 101, 301, 401, 151);

-- Giornata 2 - SCHEDULED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-04-18 17:30:00', 'Campo Comunale, Frosinone', NULL, NULL, 'SCHEDULED', 101, 451, 351, 201);

-- Giornata 3 - SCHEDULED
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-04-25 15:00:00', 'Stadio Comunale, Tivoli', NULL, NULL, 'SCHEDULED', 101, 301, 451, 151);
INSERT INTO partita(id, date_time, place, goals_home, goals_away, status, torneo_id, home_team_id, away_team_id, arbitro_id) VALUES (nextval('partita_seq'), '2026-04-25 17:30:00', 'Stadio Comunale, Pomezia', NULL, NULL, 'SCHEDULED', 101, 351, 401, 201);
-- GIOCATORI
-- Ostia FC (squadra_id = 1)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Luca',      'Ferretti',   '2005-03-12', 'Portiere',   1.88, 1);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Marco',     'Rinaldi',    '2006-07-22', 'Difensore',  1.82, 1);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Davide',    'Testa',      '2005-11-05', 'Difensore',  1.80, 1);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Andrea',    'Colombo',    '2007-01-18', 'Centrocampista', 1.76, 1);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Matteo',    'Gallo',      '2006-09-30', 'Attaccante', 1.79, 1);

-- ASD Ottavia (squadra_id = 51)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Simone',    'Caruso',     '2005-05-14', 'Portiere',   1.90, 51);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Gabriele',  'Marini',     '2006-02-27', 'Difensore',  1.83, 51);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Federico',  'Esposito',   '2007-08-09', 'Difensore',  1.78, 51);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Lorenzo',   'De Luca',    '2005-12-03', 'Centrocampista', 1.75, 51);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Alessandro','Romano',     '2006-06-21', 'Attaccante', 1.81, 51);

-- AS Primavalle (squadra_id = 101)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Pietro',    'Fontana',    '2005-04-17', 'Portiere',   1.86, 101);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Riccardo',  'Conti',      '2006-10-08', 'Difensore',  1.84, 101);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Nicola',    'Mancini',    '2007-03-25', 'Difensore',  1.79, 101);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Tommaso',   'Vitale',     '2005-07-11', 'Centrocampista', 1.77, 101);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Filippo',   'Serra',      '2006-01-29', 'Attaccante', 1.80, 101);

-- Giardinetti Calcio (squadra_id = 151)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Enrico',    'Greco',      '2005-09-06', 'Portiere',   1.87, 151);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Stefano',   'Moretti',    '2006-04-14', 'Difensore',  1.81, 151);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Giorgio',   'Lombardi',   '2007-06-02', 'Difensore',  1.83, 151);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Claudio',   'Ferrari',    '2005-02-19', 'Centrocampista', 1.74, 151);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Antonio',   'Barbieri',   '2006-11-07', 'Attaccante', 1.78, 151);

-- Garbatella FC (squadra_id = 201)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Vincenzo',  'Amato',      '2005-08-23', 'Portiere',   1.89, 201);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Salvatore', 'Russo',      '2006-03-31', 'Difensore',  1.80, 201);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Pasquale',  'Giordano',   '2007-09-15', 'Difensore',  1.77, 201);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Ciro',      'De Santis',  '2005-05-28', 'Centrocampista', 1.76, 201);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Luigi',     'Sorrentino', '2006-12-10', 'Attaccante', 1.82, 201);

-- ASD La Storta (squadra_id = 251)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Massimo',   'Innocenti',  '2005-01-07', 'Portiere',   1.85, 251);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Roberto',   'Pellegrini', '2006-08-16', 'Difensore',  1.82, 251);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Bruno',     'Cattaneo',   '2007-04-22', 'Difensore',  1.79, 251);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Dario',     'Rossi',      '2005-10-13', 'Centrocampista', 1.73, 251);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Carlo',     'Benedetti',  '2006-05-04', 'Attaccante', 1.80, 251);

-- ASD Tivoli Calcio (squadra_id = 301)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Samuele',   'Proietti',   '2008-02-14', 'Portiere',   1.85, 301);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Leonardo',  'Cerroni',    '2009-05-19', 'Difensore',  1.79, 301);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Gabriele',  'Fiorucci',   '2008-11-02', 'Difensore',  1.81, 301);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Tommaso',   'Angelini',   '2009-07-25', 'Centrocampista', 1.74, 301);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Diego',     'Santarelli', '2008-09-30', 'Attaccante', 1.78, 301);

-- Pomezia Calcio (squadra_id = 351)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Nicolò',    'Bevilacqua', '2008-03-11', 'Portiere',   1.87, 351);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Alessio',   'Fabrizi',    '2009-01-27', 'Difensore',  1.80, 351);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Emanuele',  'Ranucci',    '2008-06-08', 'Difensore',  1.77, 351);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Pietro',    'Salvatori',  '2009-10-16', 'Centrocampista', 1.73, 351);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Riccardo',  'Toselli',    '2008-04-05', 'Attaccante', 1.82, 351);

-- US Civitavecchia Giovanile (squadra_id = 401)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Matteo',    'Ceccarelli', '2008-08-19', 'Portiere',   1.86, 401);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Francesco', 'Palombi',    '2009-02-22', 'Difensore',  1.78, 401);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Simone',    'Nardoni',    '2008-12-03', 'Difensore',  1.80, 401);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Davide',    'Mariotti',   '2009-05-14', 'Centrocampista', 1.75, 401);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Andrea',    'Petrucci',   '2008-07-27', 'Attaccante', 1.79, 401);

-- ASD Frosinone Giovani (squadra_id = 451)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Lorenzo',   'De Angelis',  '2008-01-09', 'Portiere',   1.84, 451);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Marco',     'Tozzi',       '2009-03-30', 'Difensore',  1.81, 451);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Christian', 'Vittori',     '2008-10-11', 'Difensore',  1.79, 451);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Alessandro','Rinaldi',     '2009-06-24', 'Centrocampista', 1.76, 451);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Federico',  'Iannucci',    '2008-09-02', 'Attaccante', 1.80, 451);

-- GIOCATORI SVINCOLATI (senza squadra, squadra_id = NULL)
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Christian', 'Bianchi',    '2005-11-12', 'Portiere',   1.87, NULL);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Fabio',     'Rizzo',      '2006-01-05', 'Difensore',  1.84, NULL);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Edoardo',   'Moretti',    '2007-04-19', 'Centrocampista', 1.76, NULL);
INSERT INTO giocatore(id, first_name, last_name, date_of_birth, role, height, squadra_id) VALUES (nextval('giocatore_seq'), 'Manuel',    'Ricci',      '2005-08-30', 'Attaccante', 1.81, NULL);