package no.hvl.dat107.oblig3.Avdeling;

import no.hvl.dat107.oblig3.Ansatt.Ansatt;
import no.hvl.dat107.oblig3.Ansatt.AnsattDAOInterface;
import no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse.Prosjektdeltakelse;
import no.hvl.dat107.oblig3.Teksgrensesnitt;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AvdelingTekstgrensesnitt extends Teksgrensesnitt {

    public static void avdelingGrensesnitt(AvdelingDAOInterface avDAO, AnsattDAOInterface anDAO) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            String promptTekst = "Velg operasjon: \n" +
                    " a) Finn avdeling med id eller navn\n" +
                    " b) Hent alle ansatte i avdeling\n" +
                    " c) Legg til ny avdeling\n" +
                    " 0) Tilbake";

            System.out.println(promptTekst);

            String input = scanner.nextLine();

            switch (input) {
                case "0": // 0) Tilbake
                    done = true;
                    break;
                case "a": // a) Finn avdeling med id eller navn
                    System.out.println("Avdeling: " + AvdelingTekstgrensesnitt.finnAvdelingMedIdEllerNavn(avDAO));
                    break;
                case "b": // b) Hent ansatt i avdeling
                    System.out.println(AvdelingTekstgrensesnitt.hentAnsatteIAvdeling(avDAO));
                    break;
                case "c": // c) Legg til ny avdeling
                    AvdelingTekstgrensesnitt.leggTilAvdeling(avDAO,anDAO);
                    System.out.println("Ny avdeling lagt til.");
                    break;
            }
        }
    }

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
    public static Avdeling finnAvdelingMedIdEllerNavn(AvdelingDAOInterface DAO) throws Exception {
        Scanner input = new Scanner(System.in);

        Avdeling avdeling = safeRead(() -> {
            System.out.print("Skriv inn id eller navn: ");
            String res = input.nextLine();
            if (res.matches("^\\d+$")) {
                Avdeling funnet = DAO.finnAvdelingMedId(Integer.parseInt(res));
                if (funnet != null) {
                    return funnet;
                }
            }
            Avdeling funnet = DAO.finnAvdelingMedNavn(res);
            if (funnet == null) {
                throw new Exception("avdeling eksisterer ikke");
            }
            return funnet;
        },"Ikke gyldig avdeling");
        return avdeling;
    }

    public static String hentAnsatteIAvdeling(AvdelingDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        return safeRead(() -> {
            System.out.print("Avdeling: ");
            String res = input.nextLine();
            List<Ansatt> ansattList;
            Avdeling avdeling;

            if (res.matches("^\\d+$")) {
                ;
                avdeling = DAO.finnAvdelingMedId(Integer.parseInt(res));
                ansattList = DAO.getAnsatte(Integer.parseInt(res));
            } else {
                avdeling = DAO.finnAvdelingMedNavn(res);
                ansattList = DAO.getAnsatte(avdeling.getId());
            }

            if (avdeling == null) {
                throw new Exception("Fant ingen avdeling");
            }


            return DAO.ansatteToString(ansattList, avdeling);
        }, "Ikke gyldig avdeling");
    }


    public static String leggTilAvdeling(AvdelingDAOInterface DAO, AnsattDAOInterface anDAO){

        String avdeling;
        String sjefsBrukernavn;

        Scanner input = new Scanner(System.in);

        avdeling = safeRead(() -> {
            System.out.println("Skriv inn nytt avdelingsnavn:");
            String res = input.nextLine();
            if(DAO.finnAvdelingMedNavn(res) != null){
                throw new Exception("Avdelingsnavn er tatt");
            }
            return res;
        }, "Ikke gyldig navn");

        sjefsBrukernavn = safeRead(() -> {
            System.out.println("Skriv inn ny sjefbrukernavn:");
            String sjef = input.nextLine();
            Ansatt ansattInfo = anDAO.finnAnsattMedBrukernavn(sjef);
            if(ansattInfo == null){
                throw new Exception("Bruker finnes ikke");
            }
            if(anDAO.erSjef(ansattInfo.getId())){
                throw new Exception("Allerede sjef i annen avdeling");
            }
            return sjef;
        }, "Ikke gyldig navn");

        Avdeling nyAvdeling = new Avdeling(avdeling,sjefsBrukernavn);
        DAO.opprettProsjekt(nyAvdeling);

        return nyAvdeling.toString();
    }
}
