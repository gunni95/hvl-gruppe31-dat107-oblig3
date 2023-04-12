package no.hvl.dat107.oblig3.Avdeling;

import no.hvl.dat107.oblig3.Ansatt.Ansatt;
import no.hvl.dat107.oblig3.Teksgrensesnitt;

import java.util.List;
import java.util.Scanner;

public class AvdelingTekstgrensesnitt extends Teksgrensesnitt {
    public static Avdeling finnAvdelingMedNavn(AvdelingDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        String sokNavn = safeRead(() -> {
            System.out.print("Skriv inn brukernavn:");
            return input.nextLine();
        }, "Ikke gyldig søkeverdi");

        return DAO.finnAvdelingMedNavn(sokNavn);
    }

    public static Avdeling finnAvdelingMedId(AvdelingDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        int id = safeRead(() -> {
            System.out.print("Skriv inn id:");
            return Integer.parseInt(input.nextLine());
        }, "Ikke gyldig søkeverdi");

        return DAO.finnAvdelingMedId(id);
    }

    public static List<Ansatt> hentAnsatteIAvdeling(AvdelingDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        return safeRead(() -> {
            System.out.print("Avdeling id: ");
            int id = Integer.parseInt(input.nextLine());

            List<Ansatt> ansattList = DAO.getAnsatte(id);
            Avdeling avdeling = DAO.finnAvdelingMedId(id);

            ansattList.sort((a, b) -> a.getBrukernavn().equals(avdeling.getAvdelingSjef()) ? -1 : a.getId() - b.getId() + 1);
            return ansattList;
        }, "Ikke gyldig avdeling");
    }
}
