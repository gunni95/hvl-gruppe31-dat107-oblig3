package no.hvl.dat107.oblig3.Prosjekt;

import no.hvl.dat107.oblig3.Ansatt.Ansatt;

import java.util.List;

public interface ProsjektDAOInterface {

        /** Create - Opprette ny Prosjekt-rad i databasen */
        void lagreProsjekt(Prosjekt a);

        /** Read1 - Hente ut Prosjekt fra databasen. En enkelt med id */
        Prosjekt finnProsjektMedId(int id);

        /** Read1.5 - - Hente ut Prosjekt fra databasen. En enkel med navn */
        Prosjekt finnProsjektMedNavn(String ProsjektNavn);

        /** Read2 - Hente ut Prosjekt fra databasen. Alle Prosjekter. */
        List<Prosjekt> hentAlleProsjekter();


        /** Update - Oppdatere en Prosjekt-rad i databasen */
        void oppdaterProsjekt(int id, String nyttProsjektNavn, String nySjef, String nyBeskrivelse);

        List<Ansatt> getMedlemmer(Integer id);

        String getProsjektInfo(Integer prosjektId);


        /** Delete - Slette en Prosjekt-rad fra databasen */
        void slettProsjekt(int id);
}
