DROP SCHEMA IF EXISTS oblig3 CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO oblig3;

CREATE TABLE avdeling(
                         Id SERIAL,
                         Navn char(20) NOT NULL,
                         primary key (Id)
);
CREATE TABLE ansatt(
                       Id SERIAL primary key,
                       Brukernavn char(6) unique,
                       Fornavn char(20) NOT NULL,
                       Etternavn char(20) NOT NULL,
                       AnsettelsesDato char(20) NOT NULL,
                       Stilling char(30),
                       MaanedsLonn integer,
                       Avdeling integer,
                       foreign key (Avdeling) REFERENCES avdeling(Id)
);
CREATE TABLE prosjekt(
                         Id SERIAL primary key,
                         Prosjektnavn char(30) NOT NULL,
                         Sjef char(6) unique,
                         Beskrivelse char(100),
                         Timetall integer
);
CREATE TABLE prosjektdeltakelse(
                                   prosjektid integer,
                                   foreign key (prosjektid) REFERENCES prosjekt(Id),
                                   Rolle char(20) NOT NULL,
                                   Prosjekttimer integer,
                                   ansattid integer,
                                   foreign key (ansattid) REFERENCES ansatt(Id),
                                   primary key (prosjektid, ansattId)
);

INSERT INTO avdeling(navn)
VALUES ('Administrasojn'),
       ('Utvikling'),
       ('HR');

INSERT INTO ansatt(Brukernavn, Fornavn, Etternavn, AnsettelsesDato, Stilling, MaanedsLonn, Avdeling)
VALUES
    ('dondav', 'Don David', 'Duck', '2022-01-01', 'HR Manager', 55000, 3),
    ('dannyd', 'Danny', 'Dork', '2022-03-22', 'Økonom', 43000, 1),
    ('janola', 'Jan Olav', 'Jenssen', '2022-04-01', 'Forretningsutvikler', 45000, 2),
    ('chriss', 'Chris', 'Stavik', '2022-04-02', 'Seniorutvikler', 50000, 2),
    ('damsch', 'Dam', 'Scheim', '2022-10-12', 'Frontend utvikler', 53000, 2),
    ('fredri', 'Fredrik', 'Haraldsen', '2021-08-01', 'Daglig leder', 65000, 1);

ALTER TABLE avdeling
    ADD sjef CHAR(6);

ALTER TABLE avdeling
    ADD FOREIGN KEY (sjef) references ansatt(brukernavn);

UPDATE avdeling
SET sjef = 'fredri'
WHERE Id = 1;

UPDATE avdeling
SET sjef = 'janola'
WHERE Id = 2;

UPDATE avdeling
SET sjef = 'dondav'
WHERE Id = 3;