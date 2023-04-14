package no.hvl.dat107.oblig3.Prosjekt;

import no.hvl.dat107.oblig3.Ansatt.AnsattDAO;
import no.hvl.dat107.oblig3.Ansatt.AnsattDAOInterface;
import no.hvl.dat107.oblig3.Avdeling.AvdelingDAOInterface;
import no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse.Prosjektdeltakelse;
import no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse.ProsjektdeltakelseDAO;
import no.hvl.dat107.oblig3.Teksgrensesnitt;

import java.util.Scanner;

public class ProsjektTekstgrensesnitt extends Teksgrensesnitt {

    public static void prosjektGrensesnitt(AvdelingDAOInterface avDAO, AnsattDAOInterface anDAO, ProsjektDAOInterface prDAO) {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            String promptTekst = "Velg operasjon:" +
                    " a) Legg til nytt prosjekt\n " +
                    " b) oppdater prosjekt\n" +
                    " c) Registrere prosjektdeltakelse\n " +
                    " d) Føre timer\n " +
                    " e) Skrive ut prosjektinformasjon\n" +
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
                case "b": // b) Skrive ut prosjektinformasjon
                    ProsjektTekstgrensesnitt.oppdaterProsjekt(prDAO);
                    System.out.println("Prosjekt oppdatert");
                    break;

                case "c": // b) Registrere prosjektdeltakelse
                    ProsjektTekstgrensesnitt.leggTilDeltaker(prDAO);
                    System.out.println("Ny prosjekt deltaker lagt til.");
                    break;
                case "d": // c) Føre timer
                    ProsjektTekstgrensesnitt.leggTilTimer(prDAO);
                    System.out.println("Nye timer lagt til.");

                    break;
                case "e": // e) Skriv ut prosjekt informasjon
                    ProsjektTekstgrensesnitt.skrivUtBeskrivelse(prDAO);
                    System.out.println("Nye timer lagt til.");

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
    public static void leggTilDeltaker(ProsjektDAOInterface DAO){

        ProsjektDAO prDAO = new ProsjektDAO();
        AnsattDAO anDAO = new AnsattDAO();
        ProsjektdeltakelseDAO adDAO = new ProsjektdeltakelseDAO();
        Integer prosjektId;
        Integer ansattId;
        String rolle;

        Scanner input = new Scanner(System.in);

        prosjektId = safeRead(() -> {
            System.out.println("Skriv inn prosjek");
            String res = input.nextLine();
            return prDAO.finnProsjektMedNavn(res).getId();
        }, "Ikke gyldig prosjekt");

        ansattId = safeRead(() -> {
            System.out.println("Skriv inn navn på deltaker:");
            String res = input.nextLine();
            return anDAO.finnAnsattMedBrukernavn(res).getId();
        }, "Ikke gyldig navn");

        rolle = safeRead(() -> {
            System.out.println("Skriv inn deltaker sin rolle");
            String prosjektBeskrivelse = input.nextLine();

            return prosjektBeskrivelse;
        }, "Ikke gyldig rolle");

        Prosjektdeltakelse leggTilDeltaker = new Prosjektdeltakelse(prosjektId, ansattId, rolle);
        adDAO.opprettProsjektdeltakelse(leggTilDeltaker);
    }
    public static void leggTilTimer(ProsjektDAOInterface DAO){

        ProsjektDAO prDAO = new ProsjektDAO();
        ProsjektdeltakelseDAO pdDAO = new ProsjektdeltakelseDAO();
        AnsattDAO anDAO = new AnsattDAO();
        ProsjektdeltakelseDAO adDAO = new ProsjektdeltakelseDAO();
        Integer prosjektId;
        Integer ansattId;
        Integer timer;

        Scanner input = new Scanner(System.in);

        prosjektId = safeRead(() -> {
            System.out.println("Skriv inn prosjek");
            String res = input.nextLine();
            return prDAO.finnProsjektMedNavn(res).getId();
        }, "Ikke gyldig prosjekt");

        ansattId = safeRead(() -> {
            System.out.println("Skriv inn navn på deltaker:");
            String res = input.nextLine();
            return anDAO.finnAnsattMedBrukernavn(res).getId();
        }, "Ikke gyldig navn");

        timer = safeRead(() -> {
            System.out.println("Skriv inn antall nye timer");
            Integer antallTimer = Integer.parseInt(input.nextLine());

            return antallTimer;
        }, "Ikke gyldig time Verdi");

        pdDAO.leggTilTimer(prosjektId, ansattId, timer);
    }
    public static void oppdaterProsjekt(ProsjektDAOInterface DAO){

        ProsjektDAO prDAO = new ProsjektDAO();
        ProsjektdeltakelseDAO pdDAO = new ProsjektdeltakelseDAO();
        AnsattDAO anDAO = new AnsattDAO();
        Integer prosjektId;
        String prosjektNavn;
        String nySjef;
        String beskrivelse;

        Scanner input = new Scanner(System.in);

        prosjektId = safeRead(() -> {
            System.out.print("Prosjekt til redigering: ");
            String res = input.nextLine();
            if (res.matches("^\\d+$")) {
                Prosjekt funnet =  prDAO.finnProsjektMedId(Integer.parseInt(res));
                if (funnet != null) {
                    return funnet.getId();
                }
            }
            return prDAO.finnProsjektMedNavn(res).getId();
        }, "Ikke gyldig prosjekt");

        prosjektNavn = safeRead(() -> {
            System.out.print("Prosjekt navn: ");
            String res = input.nextLine();
            if (res.length() == 0) {
                return null;
            }

            return res;
        }, "Ikke gyldig navn");

        nySjef = safeRead(() -> {
            System.out.print("Ansatt brukernavn: ");
            String res = input.nextLine();
            if (res.length() == 0) {
                return null;
            }

            String b =  anDAO.finnAnsattMedBrukernavn(res).getBrukernavn();

            if (b == null) {
                throw new Exception("Ingen ansatt med dette brukernavnet");
            }

            return b;
        }, "Ikke gyldig ansatt");

        beskrivelse = safeRead(() -> {
            System.out.println("Skriv inn prosjekt beskrivelse");
            String prosjektBeskrivelse = (input.nextLine());

            if(prosjektBeskrivelse.length() == 0) {
                return null;
            }

            return prosjektBeskrivelse;
        }, "Ikke gyldig time Verdi");

        prDAO.oppdaterProsjekt(prosjektId, prosjektNavn, nySjef, beskrivelse);
    }
    public static Prosjekt skrivUtBeskrivelse(ProsjektDAOInterface DAO){

        String prosjekt;

        Scanner input = new Scanner(System.in);

        prosjekt = safeRead(() -> {
            System.out.println("Skriv inn prosjekt navn:");
            String res = input.nextLine();
            if(DAO.finnProsjektMedNavn(res) == null){
                throw new Exception("prosjekte eksisterer ikke");
            }
            return DAO.finnProsjektMedNavn(res).getProsjektInfo;
        }, "Ikke gyldig navn");
    }
}
