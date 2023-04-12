DROP SCHEMA IF EXISTS ansatte CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO ansatte;

CREATE TABLE ansatt(
                       Id SERIAL,
                       Brukernavn char(6),
                       Fornavn char(20) NOT NULL,
                       Etternavn char(20) NOT NULL,
                       AnsettelsesDato char(20) NOT NULL,
                       Stilling char(30),
                       MaanedsLonn integer,
                       primary key (Id, Brukernavn)
);
CREATE TABLE avdeling(
                         Id SERIAL,
                         Navn char(20) NOT NULL,
                         Sjef char(30) NOT NULL,
                         primary key (Id)
);

INSERT INTO ansatt(Brukernavn, Fornavn, Etternavn, AnsettelsesDato, Stilling, MaanedsLonn)
VALUES
    ('Don', 'Don', 'Duck', '2022-01-01', 'Kokk', 40000),
    ('Dan', 'Danny', 'Dork', '2022-03-22', 'Murer', 43000),
    ('Dam', 'Dam', 'Scheim', '2022-10-12', 'Snikskytter', 48000);