package no.hvl.dat107.oblig3.Prosjekt;

import no.hvl.dat107.oblig3.Ansatt.AnsattDAOInterface;
import no.hvl.dat107.oblig3.Avdeling.AvdelingDAOInterface;
import no.hvl.dat107.oblig3.Teksgrensesnitt;

import java.util.Scanner;

public class ProsjektTekstgrensesnitt extends Teksgrensesnitt {

    public static void prosjektGrensesnitt(AvdelingDAOInterface avDAO, AnsattDAOInterface anDAO, ProsjektDAOInterface prDAO) {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            String promptTekst = "Velg operasjon:" +
                    " a) Legg til nytt prosjekt\n " +
                    " b) Registrere prosjektdeltakelse\n " +
                    " c) Føre timer\n " +
                    " d) Skrive ut prosjektinformasjon\n" +
                    " 0) Tilbake";

            System.out.println(promptTekst);

            String input = scanner.nextLine();

            switch (input) {
                case "0": // 0) Tilbake
                    done = true;
                    break;
                case "a": // a) Legg til nytt prosjekt
                    ProsjektTekstgrensesnitt.opprettProsjekt(prDAO, anDAO);
                    System.out.println("Ny avdeling lagt til.");

                    break;
                case "b": // b) Registrere prosjektdeltakelse


                    break;
                case "c": // c) Føre timer

                    break;
                case "d": // b) Skrive ut prosjektinformasjon

                    break;
            }
        }
    }

    public static Prosjekt finnProsjektMedNavn(no.hvl.dat107.oblig3.Prosjekt.ProsjektDAO DAO) {
        Scanner input = new Scanner(System.in);

        String sokNavn = safeRead(() -> {
            System.out.print("Skriv inn brukernavn:");
            return input.nextLine();
        }, "Ikke gyldig søkeverdi");

        return DAO.finnProsjektMedNavn(sokNavn);
    }

    public static Prosjekt finnProsjektMedId(no.hvl.dat107.oblig3.Prosjekt.ProsjektDAO DAO) {
        Scanner input = new Scanner(System.in);

        int id = safeRead(() -> {
            System.out.print("Skriv inn id:");
            return Integer.parseInt(input.nextLine());
        }, "Ikke gyldig søkeverdi");

        return DAO.finnProsjektMedId(id);
    }

    public static Prosjekt opprettProsjekt(ProsjektDAOInterface prDAO, AnsattDAOInterface anDAO){

        String prosjekt;
        String sjef;
        String beskrivelse;

        Scanner input = new Scanner(System.in);

        prosjekt = safeRead(() -> {
            System.out.print("Prosjekt navn:");
            String res = input.nextLine();
            if(prDAO.finnProsjektMedNavn(res) != null){
                throw new Exception("prosjekt navn er tatt");
            }
            return res;
        }, "Ikke gyldig navn");

        sjef = safeRead(() -> {
            System.out.print("Sjef: ");
            String res = input.nextLine();
            if (anDAO.finnAnsattMedBrukernavn(res) == null) {
                throw new Exception("finner ingen ansatt med dette brukernanvnet");
            }
            return res;
        }, "Ikke gyldig sjef");

        beskrivelse = safeRead(() -> {
            System.out.println("Skriv inn prosjekt beskrivelse");
            String prosjektBeskrivelse = input.nextLine();

            return prosjektBeskrivelse;
        }, "Ikke gyldig beskrivelse");

        Prosjekt nyttProsjekt = new Prosjekt(prosjekt, sjef, beskrivelse);
        prDAO.lagreProsjekt(nyttProsjekt);
        return nyttProsjekt;
    }
}
