INSERT INTO groups (name) values ('Imperva Management Group');

-- Rank 1
INSERT INTO teams (name, rank) values ('Russia', 1);
INSERT INTO teams (name, rank) values ('Germany', 1);
INSERT INTO teams (name, rank) values ('Brazil', 1);
INSERT INTO teams (name, rank) values ('Portugal', 1);
INSERT INTO teams (name, rank) values ('Argentina', 1);
INSERT INTO teams (name, rank) values ('Belgium ', 1);
INSERT INTO teams (name, rank) values ('Poland ', 1);
INSERT INTO teams (name, rank) values ('France', 1);

-- Rank 2
INSERT INTO teams (name, rank) values ('Spain', 2);
INSERT INTO teams (name, rank) values ('Peru', 2);
INSERT INTO teams (name, rank) values ('Switzerland', 2);
INSERT INTO teams (name, rank) values ('England', 2);
INSERT INTO teams (name, rank) values ('Colombia', 2);
INSERT INTO teams (name, rank) values ('Mexico', 2);
INSERT INTO teams (name, rank) values ('Uruguay', 2);
INSERT INTO teams (name, rank) values ('Croatia', 2);

-- Rank 3
INSERT INTO teams (name, rank) values ('Denmark', 3);
INSERT INTO teams (name, rank) values ('Iceland', 3);
INSERT INTO teams (name, rank) values ('Costa Rica', 3);
INSERT INTO teams (name, rank) values ('Sweden', 3);
INSERT INTO teams (name, rank) values ('Tunisia', 3);
INSERT INTO teams (name, rank) values ('Egypt', 3);
INSERT INTO teams (name, rank) values ('Senegal', 3);
INSERT INTO teams (name, rank) values ('Iran', 3);

-- Rank 4
INSERT INTO teams (name, rank) values ('Serbia', 4);
INSERT INTO teams (name, rank) values ('Nigeria', 4);
INSERT INTO teams (name, rank) values ('Australia', 4);
INSERT INTO teams (name, rank) values ('Japan', 4);
INSERT INTO teams (name, rank) values ('Morocco', 4);
INSERT INTO teams (name, rank) values ('Panama', 4);
INSERT INTO teams (name, rank) values ('South Korea', 4);
INSERT INTO teams (name, rank) values ('Saudi Arabia', 4);

-- Games
-- 2018-06-14
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Russia','Saudi Arabia', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-15
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Egypt','Uruguay', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Morocco','Iran', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Portugal','Spain', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-16
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('France','Australia', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Argentina','Iceland', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Peru','Denmark', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Croatia','Nigeria', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-17
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Costa Rica','Serbia', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Germany','Mexico', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Brazil','Switzerland', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-18
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Sweden','South Korea', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Belgium','Panama', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Tunisia','England', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-19
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Colombia','Japan', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Poland','Senegal', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Russia','Egypt', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-20
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Portugal','Morocco', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Uruguay','Saudi Arabia', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Iran','Spain', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-21
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Denmark','Australia', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('France','Peru', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Argentina','Croatia', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-22
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Brazil','Costa Rica', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Nigeria','Iceland', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Serbia','Switzerland', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-23
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Belgium','Tunisia', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('South Korea','Mexico', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Germany','Sweden', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-24
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('England','Panama', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Japan','Senegal', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Poland','Colombia', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-25
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Uruguay','Russia', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Saudi Arabia','Egypt', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Iran','Portugal', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Spain','Morocco', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-26
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Denmark','France', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Australia','Peru', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Nigeria','Argentina', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Iceland','Croatia', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-27
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Mexico','Sweden', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('South Korea','Germany', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Serbia','Brazil', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Switzerland','Costa Rica', CURRENT_TIMESTAMP,'Group', 0);

-- 2018-06-28
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Japan','Poland', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Senegal','Colombia', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Panama','Tunisia', CURRENT_TIMESTAMP,'Group', 0);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('England','Belgium', CURRENT_TIMESTAMP,'Group', 0);

-- Round of 16
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Winner A','Runner B', CURRENT_TIMESTAMP,'ROUND_OF_16', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Winner C','Runner D', CURRENT_TIMESTAMP,'ROUND_OF_16', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Winner B','Runner A', CURRENT_TIMESTAMP,'ROUND_OF_16', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Winner D','Runner C', CURRENT_TIMESTAMP,'ROUND_OF_16', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Winner E','Runner F', CURRENT_TIMESTAMP,'ROUND_OF_16', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Winner G','Runner H', CURRENT_TIMESTAMP,'ROUND_OF_16', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Winner F','Runner E', CURRENT_TIMESTAMP,'ROUND_OF_16', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('Winner H','Runner G', CURRENT_TIMESTAMP,'ROUND_OF_16', 1);


-- Quarter final
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('W49','W50', CURRENT_TIMESTAMP,'QUARTER_FINALS', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('W53','W54', CURRENT_TIMESTAMP,'QUARTER_FINALS', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('W51','W52', CURRENT_TIMESTAMP,'QUARTER_FINALS', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('W55','W56', CURRENT_TIMESTAMP,'QUARTER_FINALS', 1);


-- Semi final
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('W57','W58', CURRENT_TIMESTAMP,'SEMI_FINALS', 1);
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('W59','W60', CURRENT_TIMESTAMP,'SEMI_FINALS', 1);

-- 3/4
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('L61','L62', CURRENT_TIMESTAMP,'THIRD_PLACE_PLAY_OFF', 1);

-- Final
INSERT INTO games (team1, team2, game_time, stage, should_override) values ('W61','W62', CURRENT_TIMESTAMP,'FINAL', 1);