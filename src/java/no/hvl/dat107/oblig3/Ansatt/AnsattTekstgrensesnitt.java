package no.hvl.dat107.oblig3.Ansatt;
import no.hvl.dat107.oblig3.Avdeling.AvdelingDAOInterface;
import no.hvl.dat107.oblig3.Prosjekt.Prosjekt;
import no.hvl.dat107.oblig3.Teksgrensesnitt;

import java.util.List;
import java.util.Scanner;


public class AnsattTekstgrensesnitt extends Teksgrensesnitt {

    public static void ansattGrensesnitt(AvdelingDAOInterface avDAO, AnsattDAOInterface anDAO) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            String promptTekst = "Velg operasjon:\n " +
                    " a) Søk etter ansatt med id eller brukernavn\n " +
                    " b) Liste med ansatte\n " +
                    " c) Oppdatere ansatt\n " +
                    " d) Legg til ny ansatt\n " +
                    " 0) Tilbake";

            System.out.println(promptTekst);

            String input = scanner.nextLine();

            switch (input) {
                case "0": // 0) Tilbake
                    done = true;
                    break;
                case "a": // a) Søk ansatt med id eller brukernavn
                    System.out.println("Funnet: " + AnsattTekstgrensesnitt.finnAnsatMedIdEllerBrukernavn(anDAO));
                    break;
                case "b": // c) Liste med ansatt
                    System.out.println("Alle ansatte: " + AnsattTekstgrensesnitt.listAnsatte(anDAO));
                    break;
                case "c": // d) Oppdatere ansatt
                    System.out.println("Oppdatert ansatt: " + AnsattTekstgrensesnitt.oppdaterAnsatt(avDAO, anDAO));
                    break;
                case "d": // e) Legg til ny ansatt
                    System.out.println("Ny ansatt: " + AnsattTekstgrensesnitt.LesInnNyAnsatt(avDAO, anDAO));
                    break;
            }
        }
    }

    public static Ansatt LesInnNyAnsatt(AvdelingDAOInterface avDAO, AnsattDAOInterface anDAO) {
        String brukernavn;
        String fornavn;
        String etternavn;
        String ansettelsesdato;
        String stilling;
        Integer maanedslonn;
        int avdeling;

        Scanner input = new Scanner(System.in);

        brukernavn = safeRead(() -> {
            System.out.print("Brukernavn: ");
            String res = input.nextLine();
            if (anDAO.finnAnsattMedBrukernavn(res) != null) {
                throw new Exception("Brukernavn er allerede tatt");
            }
            return res;
        }, "Ikke gyldig brukernavn");

        fornavn = safeRead(() -> {
            System.out.print("Fornavn: ");
            return input.nextLine();
        }, "Ikke gyldig fornavn");

        etternavn = safeRead(() -> {
            System.out.print("Etternavn: ");
            return input.nextLine();
        }, "Ikke gyldig etternavn");

        ansettelsesdato = safeRead(() -> {
            System.out.print("dato (dd.mm.yyyy): ");
            String res = input.nextLine();
            String[] dateSections = res.split("\\-|\\.| ");
            int day = Integer.parseInt(dateSections[0]);
            int month = Integer.parseInt(dateSections[1]);
            int year = Integer.parseInt(dateSections[2]);
            System.out.println(day + ", " + month + ", " + year);
            if (day > 31 || day < 1 || month < 1 || month > 12 || Integer.toString(year).length() == 0) {
                throw new Exception("Ikke gyldig datoformat");
            }
            String formatedDay = String.format("%2d", day);
            String formatedMonth = String.format("%2d", month);
            String formatedYear = String.format("%4d", year);

            return formatedYear + "." + formatedMonth + "." + formatedDay;
        }, "Ikke gyldig dato");

        avdeling = safeRead(() -> {
            System.out.print("Avdeling: ");
            String res = input.nextLine();
            try {
               return avDAO.finnAvdelingMedId(Integer.parseInt(res)).getId();
            } catch (Exception e){
                return avDAO.finnAvdelingMedNavn(res).getId();
            }
        }, "Ikke gyldig avdeling");

        stilling = safeRead(() -> {
            System.out.print("Stilling: ");
            return input.nextLine();
        }, "Ikke gyldig stilling");

        maanedslonn = safeRead(() -> {
            System.out.print("Månedslønn: ");
            return Integer.parseInt(input.nextLine());
        }, "Ikke gyldig lønn");

        Ansatt nyAnsatt = new Ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, avdeling, stilling, maanedslonn);
        anDAO.opprettAnsatt(nyAnsatt);
        return nyAnsatt;
    }

    public static Ansatt oppdaterAnsatt(AvdelingDAOInterface avDAO, AnsattDAOInterface anDAO) {
        Scanner input = new Scanner(System.in);
        Integer sokId = safeRead(() -> {
            System.out.print("Skriv id på ansatt du vil oppdatere: ");
            String res = input.nextLine();
            if (res.length() == 0) {
                return null;
            }
            return Integer.parseInt(res);
        }, "Ikke gyldig Ansatt id");

        boolean kanEndreStilling = !anDAO.erSjef(sokId);
        Integer nyAvdeling = null;
        String nyStilling = null;

        if (kanEndreStilling) {
            nyAvdeling = safeRead(() -> {
                System.out.print("Skriv inn ny avdeling: ");
                String res = input.nextLine();
                if (res.length() == 0) {
                    return null;
                }
                return Integer.parseInt(res);
            }, "Ikke gyldig Avdeling");

            nyStilling = safeRead(() -> {
                System.out.print("Skriv inn ny stilling: ");
                String res = input.nextLine();
                if (res.length() == 0) {
                    return null;
                }
                return res;
            }, "Ikke gyldig stilling");
        }

        Integer nyLonn = safeRead(() -> {
            System.out.println("Skriv inn ny lønn:");
            String res = input.nextLine();
            if (res.length() == 0) {
                return null;
            }
            return Integer.parseInt(res);
        }, "Ikke gyldig lønn");

        if (nyAvdeling != null) {
            anDAO.oppdaterAvdeling(sokId, nyAvdeling);
        }
        if (nyStilling != null) {
            anDAO.oppdaterStilling(sokId, nyStilling);
        }
        if (nyLonn != null) {
            anDAO.oppdaterLonn(sokId, nyLonn);
        }
        return anDAO.finnAnsattMedId(sokId);
    }

    public static Ansatt finnAnsattMedBrukernavn(AnsattDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        String sokBrukernavn = safeRead(() -> {
            System.out.print("Skriv inn brukernavn: ");
            return input.nextLine();
        }, "Ikke gyldig søkeverdi");

        return DAO.finnAnsattMedBrukernavn(sokBrukernavn);
    }

    public static Ansatt finnAnsattMedId(AnsattDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        int brukerId = safeRead(() -> {
            System.out.print("Skriv inn id: ");
            return Integer.parseInt(input.nextLine());
        }, "Ikke gyldig søkeverdi");

        return DAO.finnAnsattMedId(brukerId);
    }
    public static Ansatt finnAnsatMedIdEllerBrukernavn(AnsattDAOInterface DAO) throws Exception {
        Scanner input = new Scanner(System.in);

        Ansatt bruker = safeRead(() -> {
            System.out.print("Skriv inn id eller brukernavn: ");
            String res = input.nextLine();
            if (res.matches("^\\d+$")) {
                Ansatt funnet = DAO.finnAnsattMedId(Integer.parseInt(res));
                if (funnet != null) {
                   return funnet;
                }
            }
            Ansatt funnet = DAO.finnAnsattMedBrukernavn(res);
            if (funnet == null) {
                throw new Exception("ansatt eksisterer ikke");
            }
            return funnet;
        },"Ikke gyldig prosjekt");
        return bruker;
    }


    public static List<Ansatt> listAnsatte(AnsattDAOInterface DAO) {
        return DAO.hentAlleAnsatte();
    }

    public static void skrivUt(AnsattDAOInterface DAO, String tekst) {
        List<Ansatt> personer = DAO.hentAlleAnsatte();
        System.out.println("\n--- "+ tekst +" ---");
        personer.forEach(System.out::println);
    }
}
