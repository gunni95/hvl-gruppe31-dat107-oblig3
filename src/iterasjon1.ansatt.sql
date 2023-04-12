DROP SCHEMA IF EXISTS ansatte CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO ansatte;

CREATE TABLE ansatt(
	Id integer PRIMARY KEY,
	Brukernavn char(6) PRIMARY KEY,
	Fornavn char(20) NOT NULL,
	Etternavn char(20) NOT NULL,
	AnsettelsesDato char(20) NOT NULL,
	Stilling char(30),
	MaanedsLonn integer
);

INSERT INTO ansatt(Id, Brukernavn, Fornavn, Etternavn, AnsettelsesDato, Stilling, MaanedsLonn)
VALUES
	(0001, 'Don', 'Don', 'Duck', '2022-01-01', 'Kokk', 40000),
	(0002, 'Dan', 'Danny', 'Dork', '2022-03-22', 'Murer', 43000),
	(0003, 'Dam', 'Dam', 'Scheim', '2022-10-12', 'Snikskytter', 48000);