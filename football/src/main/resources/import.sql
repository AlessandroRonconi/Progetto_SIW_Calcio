-- ── CREDENTIALS ──────────────────────────────────────────────
INSERT INTO credentials (id, username, password, role) VALUES(nextval('credentials_seq'), 'paolo', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'ADMIN');

-- ── TOURNAMENTS ───────────────────────────────────────────────
INSERT INTO tournament(id, name, year, description) VALUES (nextval('tournament_seq'), 'Under 21', 2026, 'Torneo amatoriale per gli under 21.');
INSERT INTO tournament(id, name, year, description) VALUES (nextval('tournament_seq'), 'Under 21', 2025, 'Torneo amatoriale per gli under 21.');
INSERT INTO tournament(id, name, year, description) VALUES (nextval('tournament_seq'), 'Under 18', 2026, 'Torneo amatoriale per gli under 18.');

-- ── TEAMS ─────────────────────────────────────────────────────
INSERT INTO team(id, name, city, year_of_foundation) VALUES (nextval('team_seq'), 'Roma Nord FC', 'Roma',    2005);
INSERT INTO team(id, name, city, year_of_foundation) VALUES (nextval('team_seq'), 'Lazio Sud SC', 'Roma',    2008);
INSERT INTO team(id, name, city, year_of_foundation) VALUES (nextval('team_seq'), 'Milano United', 'Milano',  2003);
INSERT INTO team(id, name, city, year_of_foundation) VALUES (nextval('team_seq'), 'Torino Academy', 'Torino',  2010);
INSERT INTO team(id, name, city, year_of_foundation) VALUES (nextval('team_seq'), 'Napoli Young', 'Napoli',  2007);
INSERT INTO team(id, name, city, year_of_foundation) VALUES (nextval('team_seq'), 'Fiorentina Boys', 'Firenze', 2006);

-- ── TOURNAMENT-TEAM (torneo Under 21 2026, id=1) ──────────────
INSERT INTO tournament_teams(tournament_id, teams_id) VALUES (1, 1);
INSERT INTO tournament_teams(tournament_id, teams_id) VALUES (1, 51);
INSERT INTO tournament_teams(tournament_id, teams_id) VALUES (1, 101);
INSERT INTO tournament_teams(tournament_id, teams_id) VALUES (1, 151);
INSERT INTO tournament_teams(tournament_id, teams_id) VALUES (1, 201);
INSERT INTO tournament_teams(tournament_id, teams_id) VALUES (1, 251);

-- ── GAMES (tournament_id=1) ───────────────────────────────────
-- Giornata 1 – tutte PLAYED
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-07 15:00:00', 'Stadio Olimpico, Roma', 2, 1, 'PLAYED', 1, 1, 51);
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-07 17:30:00', 'Arena Milano, Milano', 1, 1, 'PLAYED', 1, 51, 151);
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-07 20:00:00', 'Stadio Maradona, Napoli', 0, 3, 'PLAYED', 1, 201, 251);

-- Giornata 2 – tutte PLAYED
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-14 15:00:00', 'Arena Milano, Milano', 2, 0, 'PLAYED', 1, 101, 1);
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-14 17:30:00', 'Stadio Franchi, Firenze', 1, 2, 'PLAYED', 1, 251, 151);
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-14 20:00:00', 'Stadio Olimpico, Roma', 0, 0, 'PLAYED', 1, 51, 201);

-- Giornata 3 – una LIVE, due SCHEDULED
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-21 15:00:00', 'Stadio Olimpico, Torino', 1, 0, 'LIVE', 1, 151, 1);
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-21 17:30:00', 'Stadio Maradona, Napoli', NULL, NULL, 'SCHEDULED', 1, 201, 101);
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-21 20:00:00', 'Stadio Franchi, Firenze', NULL, NULL, 'SCHEDULED', 1, 251, 51);

-- Giornata 4 – tutte SCHEDULED
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-28 15:00:00', 'Stadio Olimpico, Roma', NULL, NULL, 'SCHEDULED', 1, 1, 201);
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-28 17:30:00', 'Arena Milano, Milano', NULL, NULL, 'SCHEDULED', 1, 101, 251);
INSERT INTO game(id, date_time, place, goals_home, goals_away, status, tournament_id, home_team_id, away_team_id) VALUES (nextval('game_seq'), '2026-03-28 20:00:00', 'Stadio Olimpico, Torino', NULL, NULL, 'SCHEDULED', 1, 151, 51);