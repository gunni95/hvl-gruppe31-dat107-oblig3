package no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse;

public interface ProsjektdeltakelseDAOInterface {
    /** Update - Oppdater rolle*/
    void oppdaterProsjektdeltakelseRolle(int id, String nyRolle);
    /** Update - Før inn timer*/
    void addTimer(int timer);
}
