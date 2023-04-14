package no.hvl.dat107.oblig3.Prosjekt;

import no.hvl.dat107.oblig3.Ansatt.Ansatt;
import no.hvl.dat107.oblig3.Ansatt.AnsattDAO;
import no.hvl.dat107.oblig3.Ansatt.AnsattDAOInterface;
import no.hvl.dat107.oblig3.Avdeling.AvdelingDAOInterface;
import no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse.Prosjektdeltakelse;
import no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse.ProsjektdeltakelseDAO;
import no.hvl.dat107.oblig3.Teksgrensesnitt;

import java.util.Scanner;

public class ProsjektTekstgrensesnitt extends Teksgrensesnitt {

    public static void prosjektGrensesnitt(AvdelingDAOInterface avDAO, AnsattDAOInterface anDAO, ProsjektDAOInterface prDAO, ProsjektdeltakelseDAO pdDAO) {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            String promptTekst = "Velg operasjon:\n" +
                    " a) Legg til nytt prosjekt\n" +
                    " b) oppdater prosjekt\n" +
                    " c) Registrere prosjektdeltakelse\n" +
                    " d) Føre timer\n" +
                    " e) Skrive ut prosjektinformasjon\n" +
                    " 0) Tilbake";

            System.out.println(promptTekst);

            String input = scanner.nextLine();

            switch (input) {
                case "0": // 0) Tilbake
                    done = true;
                    break;
                case "a": // a) Legg til nytt prosjekt
                    ProsjektTekstgrensesnitt.opprettProsjekt(prDAO, anDAO, pdDAO);
                    System.out.println("Ny avdeling lagt til.");
                    break;
                case "b": // b) oppdater prosjekt
                    ProsjektTekstgrensesnitt.oppdaterProsjekt(prDAO);
                    System.out.println("Prosjekt oppdatert");
                    break;

                case "c": // c) Registrere prosjektdeltakelse
                    ProsjektTekstgrensesnitt.leggTilDeltaker(prDAO);
                    System.out.println("Ny prosjekt deltaker lagt til.");
                    break;

                case "d": // d) Føre timer
                    ProsjektTekstgrensesnitt.leggTilTimer(prDAO);
                    break;

                case "e": // e) Skriv ut prosjekt informasjon
                    System.out.println(ProsjektTekstgrensesnitt.hentProsjektInfo(prDAO));
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

    public static Prosjekt opprettProsjekt(ProsjektDAOInterface prDAO, AnsattDAOInterface anDAO, ProsjektdeltakelseDAO pdDAO){

        String prosjekt;
        String sjef;
        String beskrivelse;

        Scanner input = new Scanner(System.in);

        prosjekt = safeRead(() -> {
            System.out.print("Prosjekt navn: ");
            String res = input.nextLine();
            if(prDAO.finnProsjektMedNavn(res) != null){
                throw new Exception("prosjekt navn er tatt");
            }
            return res;
        }, "Ikke gyldig navn");

        sjef = safeRead(() -> {
            System.out.print("Sjef: ");
            String res = input.nextLine();
            Ansatt funnet = anDAO.finnAnsattMedBrukernavn(res);
            if (funnet == null) {
                throw new Exception("finner ingen ansatt med dette brukernanvnet");
            }
            return res;
        }, "Ikke gyldig sjef");


        beskrivelse = safeRead(() -> {
            System.out.print("beskrivelse: ");
            String prosjektBeskrivelse = input.nextLine();

            if (prosjektBeskrivelse.length() == 0) {
                throw new Exception("Vennlight fyll inn en beskrivelse");
            }

            return prosjektBeskrivelse;
        }, "Ikke gyldig beskrivelse");

        Prosjekt nyttProsjekt = new Prosjekt(prosjekt, sjef, beskrivelse);
        prDAO.lagreProsjekt(nyttProsjekt);
        Prosjektdeltakelse sjefEntry = new Prosjektdeltakelse(prDAO.finnProsjektMedNavn(prosjekt).getId(), anDAO.finnAnsattMedBrukernavn(sjef).getId(), "sjef");
        pdDAO.opprettProsjektdeltakelse(sjefEntry);
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
            System.out.print("Prosjekt: ");
            String res = input.nextLine();
            if (res.matches("^\\d+$")) {
                Prosjekt funnet = prDAO.finnProsjektMedId(Integer.parseInt(res));

                if (funnet != null) {
                    return funnet.getId();
                }
            }

            Prosjekt funnet = prDAO.finnProsjektMedNavn(res);

            if (funnet == null) {
                throw new Exception("Ingen prosjekt funnet");
            }
            return funnet.getId();
        }, "Ikke gyldig prosjekt");

        ansattId = safeRead(() -> {
            System.out.print("Deltaker: ");
            String res = input.nextLine();
            if (res.matches("^\\d+$")) {
                Ansatt funnet = anDAO.finnAnsattMedId(Integer.parseInt(res));

                if (funnet != null) {
                    return funnet.getId();
                }
            }
            Ansatt funnet =  anDAO.finnAnsattMedBrukernavn(res);
            if (funnet == null) {
                throw new Exception("Ingen ansatt funnet");
            }

            return funnet.getId();
        }, "Ikke gyldig navn");

        rolle = safeRead(() -> {
            System.out.println("Skriv inn deltaker sin rolle");
            String prosjektBeskrivelse = input.nextLine();
            if (prosjektBeskrivelse.length() == 0) {
                throw new Exception("Vennlight skriv en beskrivelse");
            }

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
            System.out.print("Prosjekt: ");
            String res = input.nextLine();
            if (res.matches("^\\d+$")) {
                Prosjekt funnet = prDAO.finnProsjektMedId(Integer.parseInt(res));

                if (funnet != null) {
                    return funnet.getId();
                }
            }

            Prosjekt funnet = prDAO.finnProsjektMedNavn(res);

            if (funnet == null) {
                throw new Exception("Ingen prosjekt funnet");
            }
            return funnet.getId();
        }, "Ikke gyldig prosjekt");

        ansattId = safeRead(() -> {
            System.out.print("Deltaker: ");
            String res = input.nextLine();
            if (res.matches("^\\d+$")) {
                Ansatt funnet = anDAO.finnAnsattMedId(Integer.parseInt(res));

                if (funnet != null) {
                    return funnet.getId();
                }
            }
            Ansatt funnet =  anDAO.finnAnsattMedBrukernavn(res);
            if (funnet == null) {
                throw new Exception("Ingen ansatt funnet");
            }

            return funnet.getId();
        }, "Ikke gyldig navn");

        timer = safeRead(() -> {
            System.out.print("Antall nye timer: ");

            return Integer.parseInt(input.nextLine());
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
                Prosjekt funnet = prDAO.finnProsjektMedId(Integer.parseInt(res));

                if (funnet != null) {
                    return funnet.getId();
                }
            }

            Prosjekt funnet = prDAO.finnProsjektMedNavn(res);

            if (funnet == null) {
                throw new Exception("Ingen prosjekt funnet");
            }
            return funnet.getId();
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

            Ansatt b =  anDAO.finnAnsattMedBrukernavn(res);

            if (b == null) {
                throw new Exception("Ingen ansatt med dette brukernavnet");
            }

            return b.getBrukernavn();
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
    public static String hentProsjektInfo(ProsjektDAOInterface DAO){
        Scanner input = new Scanner(System.in);

        return safeRead(() -> {
            System.out.println("Skriv inn prosjekt navn:");
            String res = input.nextLine();
            if (res.matches("^\\d+$")) {
                Prosjekt funnet = DAO.finnProsjektMedId(Integer.parseInt(res));
                if (funnet != null) {
                    return funnet.toString();
                }
            }
            Prosjekt funnet = DAO.finnProsjektMedNavn(res);
            if(funnet == null){
                throw new Exception("prosjekte eksisterer ikke");
            }

            return funnet.toString();
        }, "Ikke gyldig navn");
    }
}