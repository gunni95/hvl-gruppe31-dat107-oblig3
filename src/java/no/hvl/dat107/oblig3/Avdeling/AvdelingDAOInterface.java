package no.hvl.dat107.oblig3.Avdeling;

import java.util.List;

public interface AvdelingDAOInterface {

        /** Create - Opprette ny Avdeling-rad i databasen */
        void lagreAvdeling(Avdeling a);

        /** Read1 - Hente ut avdeling fra databasen. En enkelt med id */
        Avdeling finnAvdelingMedId(int id);

        /** Read1.5 - - Hente ut avdeling fra databasen. En enkel med navn */
        Avdeling finnAvdelingMedNavn(String avdelingNavn);

        /** Read2 - Hente ut avdeling fra databasen. Alle Avdelinger. */
        List<Avdeling> hentAlleAvdelinger();


        /** Update - Oppdatere en avdeling-rad i databasen */
        void oppdaterAvdeling(int id, String nyttAvdelingNavn, String nySjef);

        /** Delete - Slette en Avdeling-rad fra databasen */
        void slettAvdeling(int id);
}
