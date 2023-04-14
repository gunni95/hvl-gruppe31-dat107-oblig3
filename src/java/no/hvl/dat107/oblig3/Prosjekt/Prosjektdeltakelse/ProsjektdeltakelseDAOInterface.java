package no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse;

import jakarta.persistence.EntityManager;

public interface ProsjektdeltakelseDAOInterface {
    /** Create - Create prosjektdeltakelse*/
    void opprettProsjektdeltakelse(Prosjektdeltakelse prosjektdeltakelse);

    /** Get - Henter prosjektdeltakelse*/
    Prosjektdeltakelse getProsjektdeltakelse(Integer prosjektId, Integer ansattId);

    /** Update - Oppdater rolle*/
    Prosjektdeltakelse oppdaterProsjektdeltakelseRolle(Integer prosjektId, Integer ansattId, String nyRolle);
    /** Update - Før inn timer*/
    void leggTilTimer(Integer prosjektId, Integer ansattId,  Integer antallTimer);
}
